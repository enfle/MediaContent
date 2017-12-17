package com.enfle.android.mediacontent.exceptions;

import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by : Subham Tyagi
 * Created on :  27/10/16.
 */

public class NoInternetConnection extends RuntimeException {

    public NoInternetConnection(String message) {
        super(message);
    }

    public NoInternetConnection(String message, Throwable cause) {
        super(message, cause);
    }

    public NoInternetConnection(Throwable cause) {
        super(cause);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public NoInternetConnection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
