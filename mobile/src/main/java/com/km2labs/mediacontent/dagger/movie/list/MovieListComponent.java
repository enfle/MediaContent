package com.km2labs.mediacontent.dagger.movie.list;

import com.km2labs.mediacontent.dagger.core.ui.activity.ActivityComponent;
import com.km2labs.mediacontent.dagger.core.ui.activity.ActivityComponentBuilder;
import com.km2labs.mediacontent.dagger.core.scope.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by : Subham Tyagi
 * Created on :  29/08/16.
 */

@Subcomponent(
        modules = {
                MovieListModule.class
        })
@ActivityScope
public interface MovieListComponent extends ActivityComponent<MovieListActivity> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<MovieListModule, MovieListComponent> {
    }

    void inject(MovieListFragment movieListFragment);
}
