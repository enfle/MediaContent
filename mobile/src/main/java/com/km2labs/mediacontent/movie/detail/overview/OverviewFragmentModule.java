package com.km2labs.mediacontent.movie.detail.overview;

import com.km2labs.mediacontent.dagger.scope.PerChildFragment;
import com.km2labs.mediacontent.movie.detail.recomnded.RecomdedMovieFragmentModule;
import com.km2labs.mediacontent.movie.detail.recomnded.RecommendedMovieFragment;
import com.km2labs.mediacontent.movie.detail.similar.SimilarMovieFragment;
import com.km2labs.mediacontent.movie.detail.similar.SimilarMovieModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by subhamtyagi on 14/12/17.
 */

@Module(includes = MovieOverviewModule.class)
public abstract class OverviewFragmentModule {

    @PerChildFragment
    @ContributesAndroidInjector(modules = {RecomdedMovieFragmentModule.class})
    abstract public RecommendedMovieFragment recommendedMovieFragment();

    @PerChildFragment
    @ContributesAndroidInjector(modules = {SimilarMovieModule.class})
    abstract public SimilarMovieFragment similarMovieFragment();

}
