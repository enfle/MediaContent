package com.km2labs.mediacontent.core.exception;

/**
 * Created by : Subham Tyagi
 * Created on :  27/10/16.
 */

public class NoInternetConnection extends RuntimeException {

    public NoInternetConnection() {
    }

    public NoInternetConnection(String message) {
        super(message);
    }

    public NoInternetConnection(String message, Throwable cause) {
        super(message, cause);
    }

    public NoInternetConnection(Throwable cause) {
        super(cause);
    }

    public NoInternetConnection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
