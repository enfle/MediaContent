package com.km2labs.framework.network;

import rx.Observable;

/**
 * Created by : Subham Tyagi
 * Created on :  27/10/16.
 */

public class NetworkHelper {

    public static Observable<Boolean> isDeviceConnected() {
        return Observable.fromCallable(() -> true);
    }
}
