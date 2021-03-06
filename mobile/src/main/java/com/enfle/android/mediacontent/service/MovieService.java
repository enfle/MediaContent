package com.enfle.android.mediacontent.service;


import com.enfle.android.mediacontent.beans.Images;
import com.enfle.android.mediacontent.beans.MovieDetailDto;
import com.enfle.android.mediacontent.beans.MovieListResponseDto;
import com.enfle.android.mediacontent.beans.Reviews;
import com.enfle.android.mediacontent.beans.Videos;

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

    @GET("movie/{movie_id}")
    Observable<MovieDetailDto> getMovieDetail(@Path("movie_id") int movieId, @Query("append_to_response") String query);

    @GET("movie/{movie_id}/reviews")
    Observable<Reviews> getMovieReviews(@Path("movie_id") Integer movieId);

    @GET("movie/{movie_id}/videos")
    Observable<Videos> getMovieVideos(@Path("movie_id") Integer mMovieId);

    @GET("movie/{movie_id}/images")
    Observable<Images> getMovieImages(@Path("movie_id") Integer mMovieId);
}
