package com.km2labs.mediacontent.movie.detail.video;

import com.km2labs.mediacontent.base.adapter.RecyclerItemView;
import com.km2labs.mediacontent.beans.Backdrop;
import com.km2labs.mediacontent.beans.Images;
import com.km2labs.mediacontent.beans.MovieDetailDto;
import com.km2labs.mediacontent.beans.Video;
import com.km2labs.mediacontent.beans.Videos;
import com.km2labs.mediacontent.cache.DataCache;
import com.km2labs.mediacontent.mvp.BaseNetworkPresenter;
import com.km2labs.mediacontent.service.MovieService;
import com.km2labs.mediacontent.utils.CollectionUtils;
import com.km2labs.mediacontent.utils.PaginationTool;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class VideoListPresenter extends BaseNetworkPresenter<VideoFragmentContract.View> implements VideoFragmentContract.Presenter {

    private static final int LIMIT = 20;

    private MovieService mMovieService;

    private Integer mMovieId;

    public VideoListPresenter(MovieService movieService, DataCache dataCache) {
        super(dataCache);
        mMovieService = movieService;
    }

    @Override
    public void loadVideos(Integer movieId) {
        mMovieId = movieId;
        startRequest("DefaultTag");
    }

    @Override
    protected Observable<MovieDetailDto> getApiObservable(String tag) {
        return Observable.zip(mMovieService.getMovieVideos(mMovieId), mMovieService.getMovieImages(mMovieId), (videos, images) -> {
            MovieDetailDto movieDetailDto = new MovieDetailDto();
            movieDetailDto.setVideos(videos);
            movieDetailDto.setImages(images);
            movieDetailDto.setId(mMovieId);
            return movieDetailDto;
        });
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
            getView().showEmptyScreen();
        } else
            getView().showVideoList((List<RecyclerItemView>) data);
    }

    @Override
    protected Observable<?> transformObservable(Observable<?> observable) {
        return PaginationTool.paging(getView().getRecyclerView(), offset -> observable, LIMIT);
    }

    private Observable<List<RecyclerItemView>> getRecyclerItemObserver(MovieDetailDto movieDetailDto) {
        ArrayList<RecyclerItemView> itemViews = new ArrayList<>();
        List<Backdrop> backdrops = getBackdropList(movieDetailDto);
        List<Video> videos = movieDetailDto.getVideos().getVideos();

        Backdrop prePoster = new Backdrop();
        prePoster.setFilePath(movieDetailDto.getPosterPath());
        for (int i = 0; i < videos.size(); i++) {
            Video video = videos.get(i);
            Backdrop backdrop;
            if (i < backdrops.size()) {
                backdrop = backdrops.get(i);
                prePoster = backdrop;
            } else {
                backdrop = prePoster;
            }

            itemViews.add(new VideoRecyclerView(video, backdrop));
        }

        return Observable.just(itemViews);
    }

    @Override
    public List<Backdrop> getBackdropList(MovieDetailDto movieDetailDto) {
        Images images = movieDetailDto.getImages();
        List<Backdrop> backdropList = new ArrayList<>();

        List<Video> videos = movieDetailDto.getVideos().getVideos();
        List<Backdrop> backdrops = new ArrayList<>();

        if (images != null)
            backdrops = images.getBackdrops();

        Backdrop prePoster = new Backdrop();
        prePoster.setFilePath(movieDetailDto.getPosterPath());

        for (int i = 0; i < videos.size(); i++) {
            Backdrop backdrop;
            if (i < backdrops.size()) {
                backdrop = backdrops.get(i);
                prePoster = backdrop;
            } else {
                backdrop = prePoster;
            }
            backdropList.add(backdrop);
        }
        return backdropList;
    }

    @Override
    public void loadVideos(MovieDetailDto movieDetailDto) {
        Videos videos = movieDetailDto.getVideos();
        List<Backdrop> backdrops = getBackdropList(movieDetailDto);
        List<RecyclerItemView> videoRecyclerViews = getItems(videos, backdrops);
        getView().showVideoList((videoRecyclerViews));
    }

    private List<RecyclerItemView> getItems(Videos videos, List<Backdrop> backdrops) {
        final int[] i = {0};
        List<RecyclerItemView> videoRecyclerViews = new ArrayList<>();
        Observable.fromIterable(videos.getVideos()).forEach(video -> {
            Backdrop backdrop = backdrops.get(i[0]++);
            if (backdrops.size() <= i[0]) {
                i[0] = 0;
            }
            videoRecyclerViews.add(new VideoRecyclerView(video, backdrop));
        });
        return videoRecyclerViews;
    }

    @Override
    protected void handleError(String tag, Throwable throwable) {
        getView().showErrorMessage();
    }
}
