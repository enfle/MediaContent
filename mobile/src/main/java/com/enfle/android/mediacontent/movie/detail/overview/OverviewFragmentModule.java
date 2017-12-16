package com.enfle.android.mediacontent.movie.detail.overview;

import com.enfle.android.mediacontent.dagger.scope.PerChildFragment;
import com.enfle.android.mediacontent.movie.detail.recomnded.RecomdedMovieFragmentModule;
import com.enfle.android.mediacontent.movie.detail.recomnded.RecommendedMovieFragment;
import com.enfle.android.mediacontent.movie.detail.similar.SimilarMovieFragment;
import com.enfle.android.mediacontent.movie.detail.similar.SimilarMovieModule;

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
