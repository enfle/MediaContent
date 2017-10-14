package com.km2labs.mediacontent.dagger.movie.detail.video;

import com.km2labs.mediacontent.common.cache.DataCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.bean.Images;
import com.km2labs.mediacontent.common.movie.bean.MovieDetailDto;
import com.km2labs.mediacontent.common.movie.bean.Poster;
import com.km2labs.mediacontent.common.movie.bean.Video;
import com.km2labs.mediacontent.core.adapter.RecyclerItemView;
import com.km2labs.mediacontent.core.mvp.presenter.AbsNetworkPresenter;
import com.km2labs.mediacontent.core.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class VideoListPresenter extends AbsNetworkPresenter<VideoFragmentContract.View> implements VideoFragmentContract.Presenter {

    private static final int LIMIT = 20;

    private MovieService mMovieService;

    private Integer mMovieId;

    public VideoListPresenter(MovieService movieService, DataCache dataCache) {
        mMovieService = movieService;
    }

    @Override
    public void loadVideos(Integer movieId) {
        mMovieId = movieId;
        //startLoading("DefaultTag");
    }

    @Override
    protected Observable<MovieDetailDto> getApiObservable(String tag) {
        return mMovieService.getMovieDetail(mMovieId, "videos,images");
    }

    @Override
    protected <D> Observable<?> transformResponseData(D data) {
        MovieDetailDto movieDetailDto = (MovieDetailDto) data;
        return getRecyclerItemObserver(movieDetailDto);
    }

    @Override
    protected <D> Boolean isCachedDataValid(D data) {
        return data != null;
    }

    @Override
    protected <D> void onRequestComplete(D data, String tag) {
        List<RecyclerItemView> itemViews = (List<RecyclerItemView>) data;
        if (CollectionUtils.isEmpty(itemViews)) {
            getView().onEmptyResult();
        } else
            getView().showVideoList((List<RecyclerItemView>) data);
    }

    private Observable<List<RecyclerItemView>> getRecyclerItemObserver(MovieDetailDto movieDetailDto) {
        ArrayList<RecyclerItemView> itemViews = new ArrayList<>();
        Images images = movieDetailDto.getImages();

        List<Poster> posters = new ArrayList<>();
        if (images != null)
            posters = images.getPosters();

        List<Video> videos = movieDetailDto.getVideos().getVideos();
        movieDetailDto.getBackdropPath();
        Poster prePoster = new Poster();
        prePoster.setFilePath(movieDetailDto.getPosterPath());
        for (int i = 0; i < videos.size(); i++) {
            Video video = videos.get(i);
            Poster poster = null;
            if (i < posters.size()) {
                poster = posters.get(i);
                prePoster = poster;
            } else {
                poster = prePoster;
            }
            //itemViews.add(new VideoRecyclerItemView(video, poster));
        }

        return Observable.just(itemViews);
    }

    @Override
    protected void handleError(String tag, Throwable throwable) {
        getView().showErrorMessage();
    }

    @Override
    public void retry(String tag) {

    }
}
