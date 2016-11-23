package com.km2labs.mediacontent.loaders;

import com.km2labs.framework.mvp.IPresenter;
import com.km2labs.framework.network.RetrofitHelper;
import com.km2labs.mediacontent.common.cache.InMemoryCache;
import com.km2labs.mediacontent.common.movie.MovieService;
import com.km2labs.mediacontent.common.movie.detail.MovieDetailPresenter;
import com.km2labs.mediacontent.common.movie.list.MovieListPresenter;
import com.km2labs.mediacontent.common.splash.SplashPresenter;
import com.km2labs.mediacontent.common.splash.UserAuthenticationService;
import com.km2labs.mediacontent.common.user.UserSettings;
import com.km2labs.mediacontent.loaders.core.PresenterFactory;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class DefaultPresenterFactory<T extends IPresenter> implements PresenterFactory<T> {

    public static final int TYPE_MOVIE_LIST = 1;
    public static final int TYPE_MOVIE_DETAIL = 2;
    public static final int TYPE_SPLASH = 3;

    private int mType;

    public DefaultPresenterFactory(int type) {
        this.mType = type;
    }

    @Override
    public T create() {
        switch (mType) {
            case TYPE_MOVIE_DETAIL:
                return (T) new MovieDetailPresenter(getMovieService(), new InMemoryCache());
            case TYPE_MOVIE_LIST:
                return (T) new MovieListPresenter(getMovieService(), new InMemoryCache());
            case TYPE_SPLASH:
                return (T) new SplashPresenter(UserSettings.getUserSettings(), getUserAuthenticationService(), new InMemoryCache());
            default:
        }
        return null;
    }

    private MovieService getMovieService() {
        return RetrofitHelper.getRetrofitHelper().createService(MovieService.class);
    }

    private UserAuthenticationService getUserAuthenticationService() {
        return RetrofitHelper.getRetrofitHelper().createService(UserAuthenticationService.class);
    }
}
