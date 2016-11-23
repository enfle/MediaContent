package com.km2labs.framework.network;


import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by : Subham Tyagi
 * Created on :  01/09/16.
 */

public class ExceptionTranslator {

    public static boolean isNetworkException(Throwable throwable) {

        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            int code = exception.code();
        } else if (throwable instanceof SocketTimeoutException) {
            return true;
        }
        return false;
    }

    public static boolean isAuthException(Throwable throwable) {
        return throwable instanceof IOException || throwable instanceof HttpException;
    }

    public static boolean isParsingException(Throwable throwable) {
        return throwable instanceof JsonSyntaxException;
    }
}
