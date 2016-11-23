package com.km2labs.framework.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class RetrofitHelper {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static final String API_KEY = "api_key";

    private static final RetrofitHelper RETROFIT_HELPER = new RetrofitHelper();

    public static RetrofitHelper getRetrofitHelper() {
        return RETROFIT_HELPER;
    }

    private Retrofit mRetrofit;

    private RetrofitHelper() {
    }

    public <T> T createService(Class<T> tClass) {
        return getRetrofit().create(tClass);
    }

    public Retrofit getRetrofit() {
        if (mRetrofit == null)
            mRetrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .client(getHttpClient())
                    .build();

        return mRetrofit;
    }

    @NonNull
    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
       /* gsonBuilder.registerTypeAdapter(new TypeToken<RealmList<RealmString>>() {
        }.getType(), RealmStringListTypeAdapter.INSTANCE);
        gsonBuilder.registerTypeAdapter(new TypeToken<RealmList<RealmInteger>>() {
        }.getType(), RealmIntegerListTypeAdapter.INSTANCE);*/
        return gsonBuilder.create();
    }


    @NonNull
    private OkHttpClient getHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // enable logging for debug builds
        // if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);
        //}
        httpClient.addInterceptor(this::getQueryInterceptor);
        return httpClient.build();
    }

    @NonNull
    private Response getQueryInterceptor(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                //.addQueryParameter(API_KEY, BuildConfig.API_KEY)
                .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder().url(url);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}

