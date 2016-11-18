package com.km2labs.mediacontent.dagger;

import com.km2labs.mediacontent.dagger.core.ui.activity.ActivityComponentBuilder;
import com.km2labs.mediacontent.dagger.core.scope.ActivityKey;
import com.km2labs.mediacontent.dagger.movie.detail.MovieDetailActivity;
import com.km2labs.mediacontent.dagger.movie.detail.MovieDetailComponent;
import com.km2labs.mediacontent.dagger.movie.list.MovieListActivity;
import com.km2labs.mediacontent.dagger.movie.list.MovieListComponent;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by : Subham Tyagi
 * Created on :  14/11/16.
 */

@Module(
        subcomponents = {
                MovieListComponent.class,
                MovieDetailComponent.class

        })
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MovieListActivity.class)
    public abstract ActivityComponentBuilder mainActivityComponentBuilder(MovieListComponent.Builder impl);

    @Binds
    @IntoMap
    @ActivityKey(MovieDetailActivity.class)
    public abstract ActivityComponentBuilder secondActivityComponentBuilder(MovieDetailComponent.Builder impl);
}
