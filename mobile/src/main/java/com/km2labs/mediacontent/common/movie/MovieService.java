package com.km2labs.mediacontent.common.movie;

import com.km2labs.mediacontent.common.movie.bean.MovieDetailDto;
import com.km2labs.mediacontent.common.movie.bean.MovieListResponseDto;
import com.km2labs.mediacontent.common.movie.bean.Reviews;
import com.km2labs.mediacontent.dagger.core.scope.ActivityScope;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */
@ActivityScope
public interface MovieService {

    @GET("movie/popular")
    Observable<MovieListResponseDto> getPopularMovie(@Query("page") int pageCount);

    @GET("movie/latest")
    Observable<MovieListResponseDto> getLatestMovie(@Query("page") int pageCount);

    @GET("movie/top_rated")
    Observable<MovieListResponseDto> getTopRatedMovie(@Query("page") int pageCount);

    @GET("movie/upcoming")
    Observable<MovieListResponseDto> getUpComingMovie(@Query("page") int pageCount);

    @GET("movie/{movie_id}/similar")
    Observable<MovieListResponseDto> getSimilarMovies(@Path("movie_id") Integer movieId);

    @GET("movie/{movie_id}/recommendations")
    Observable<MovieListResponseDto> getRecommendedMovies(@Path("movie_id") Integer movieId);

    @GET("movie/now_playing")
    Observable<MovieListResponseDto> getNowPlayingMovie(@Query("page") int pageCount);

    @GET("movie/{id}")
    Observable<MovieDetailDto> getMovieDetail(@Path("id") int movieId, @Query("append_to_response") String query);

    @GET("movie/{id}/reviews")
    Observable<Reviews> getMovieReviews(@Path("id") Integer movieId);
}
