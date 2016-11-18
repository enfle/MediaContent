package com.km2labs.mediacontent.dagger.movie.detail;

import com.km2labs.mediacontent.dagger.core.ui.activity.ActivityComponent;
import com.km2labs.mediacontent.dagger.core.ui.activity.ActivityComponentBuilder;
import com.km2labs.mediacontent.dagger.core.scope.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

@Subcomponent(
        modules = {
                MovieDetailModule.class
        })
@ActivityScope
public interface MovieDetailComponent extends ActivityComponent<MovieDetailActivity> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<MovieDetailModule, MovieDetailComponent> {
    }

    void inject(MovieDetailActivity movieDetailActivity);


}
