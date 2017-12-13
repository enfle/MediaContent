package com.km2labs.mediacontent.service;


import com.km2labs.mediacontent.beans.MovieDetailDto;
import com.km2labs.mediacontent.beans.MovieListResponseDto;
import com.km2labs.mediacontent.beans.Reviews;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

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
