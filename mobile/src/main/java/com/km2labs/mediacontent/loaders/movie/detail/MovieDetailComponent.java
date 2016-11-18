package com.km2labs.mediacontent.loaders.movie.detail;

import com.km2labs.mediacontent.dagger.core.scope.PersistentScope;

import dagger.Subcomponent;

/**
 * Created by : Subham Tyagi
 * Created on :  31/08/16.
 */

@Subcomponent(modules = {MovieDetailModule.class})
@PersistentScope
public interface MovieDetailComponent {
    void inject(MovieDetailActivity movieDetailActivity);

    void inject(MovieDetailFragment movieDetailFragment);
}
