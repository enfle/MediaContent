package com.enfle.android.mediacontent.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.enfle.android.mediacontent.exceptions.PagingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


/**
 * @author e.matsyuk
 */
public class PaginationTool {

    // for first start of items loading then on RecyclerView there are not items and no scrolling
    private static final int EMPTY_LIST_ITEMS_COUNT = 0;
    // default limit for requests
    private static final int DEFAULT_LIMIT = 50;
    // default max attempts to retry loading request
    private static final int MAX_ATTEMPTS_TO_RETRY_LOADING = 3;

    public static <T> Observable<T> paging(RecyclerView recyclerView, PagingListener<T> pagingListener) {
        return paging(recyclerView, pagingListener, DEFAULT_LIMIT, EMPTY_LIST_ITEMS_COUNT, MAX_ATTEMPTS_TO_RETRY_LOADING);
    }

    public static <T> Observable<T> paging(RecyclerView recyclerView, PagingListener<T> pagingListener, int limit) {
        return paging(recyclerView, pagingListener, limit, EMPTY_LIST_ITEMS_COUNT, MAX_ATTEMPTS_TO_RETRY_LOADING);
    }

    public static <T> Observable<T> paging(RecyclerView recyclerView, PagingListener<T> pagingListener, int limit, int emptyListCount) {
        return paging(recyclerView, pagingListener, limit, emptyListCount, MAX_ATTEMPTS_TO_RETRY_LOADING);
    }

    public static <T> Observable<T> paging(RecyclerView recyclerView, PagingListener<T> pagingListener, int limit, int emptyListCount, int retryCount) {
        if (recyclerView == null) {
            throw new PagingException("null recyclerView");
        }
        if (recyclerView.getAdapter() == null) {
            throw new PagingException("null recyclerView adapter");
        }
        if (limit <= 0) {
            throw new PagingException("limit must be greater then 0");
        }
        if (emptyListCount < 0) {
            throw new PagingException("emptyListCount must be not less then 0");
        }
        if (retryCount < 0) {
            throw new PagingException("retryCount must be not less then 0");
        }

        int startNumberOfRetryAttempt = 0;
        return getScrollObservable(recyclerView, limit, emptyListCount)
                .distinctUntilChanged()
                .compose(RxUtils.applySchedulers())
                .switchMap(offset -> getPagingObservable(pagingListener, pagingListener.onNextPage(offset), startNumberOfRetryAttempt, offset, retryCount));
    }

    private static Observable<Integer> getScrollObservable(RecyclerView recyclerView, int limit, int emptyListCount) {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                final RecyclerView.OnScrollListener sl = new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if (!emitter.isDisposed()) {
                            int position = getLastVisibleItemPosition(recyclerView);
                            int updatePosition = recyclerView.getAdapter().getItemCount() - 1 - (limit / 2);
                            if (position >= updatePosition) {
                                emitter.onNext(recyclerView.getAdapter().getItemCount());
                            }
                        }
                    }
                };
                recyclerView.addOnScrollListener(sl);
                emitter.setCancellable(() -> {
                    if (recyclerView.getAdapter().getItemCount() == emptyListCount) {
                        int itemCount = recyclerView.getAdapter().getItemCount();
                        int nextPage = itemCount < limit ? 1 : itemCount / limit;
                        emitter.onNext(nextPage);
                    }
                });
            }
        });
    }

    private static int getLastVisibleItemPosition(RecyclerView recyclerView) {
        Class recyclerViewLMClass = recyclerView.getLayoutManager().getClass();
        if (recyclerViewLMClass == LinearLayoutManager.class || LinearLayoutManager.class.isAssignableFrom(recyclerViewLMClass)) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            return linearLayoutManager.findLastVisibleItemPosition();
        } else if (recyclerViewLMClass == StaggeredGridLayoutManager.class || StaggeredGridLayoutManager.class.isAssignableFrom(recyclerViewLMClass)) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
            int[] into = staggeredGridLayoutManager.findLastVisibleItemPositions(null);
            List<Integer> intoList = new ArrayList<>();
            for (int i : into) {
                intoList.add(i);
            }
            return Collections.max(intoList);
        }
        throw new PagingException("Unknown LayoutManager class: " + recyclerViewLMClass.toString());
    }

    private static <T> Observable<T> getPagingObservable(PagingListener<T> listener, Observable<T> observable, int numberOfAttemptToRetry, int offset, int retryCount) {
        return observable.onErrorResumeNext(throwable -> {
            // retry to load new data portion if error occurred
            if (numberOfAttemptToRetry < retryCount) {
                int attemptToRetryInc = numberOfAttemptToRetry + 1;
                return getPagingObservable(listener, listener.onNextPage(offset), attemptToRetryInc, offset, retryCount);
            } else {
                return Observable.empty();
            }
        });
    }

}