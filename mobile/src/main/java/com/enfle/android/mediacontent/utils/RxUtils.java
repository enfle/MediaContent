/*
 * Copyright (c) 2016. . The Km2Labs Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.enfle.android.mediacontent.utils;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Subham Tyagi on 07/07/16.
 */

public final class RxUtils {
    @SuppressWarnings("unchecked")
    private static final ObservableTransformer schedulersTransformer =
            (ObservableTransformer<Observable, Observable>) upstream -> ((Observable) upstream)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(Schedulers.computation());

    @SuppressWarnings("unchecked")
    private static final ObservableTransformer mainSchedulersTransformer =
            (ObservableTransformer<Observable, Observable>) upstream -> ((Observable) upstream)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread());

    @SuppressWarnings("unchecked")
    private static final ObservableTransformer schedulersMainIOTransformer =
            (ObservableTransformer<Observable, Observable>) upstream -> ((Observable) upstream)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return (ObservableTransformer<T, T>) schedulersTransformer;
    }

    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> applyMainSchedulers() {
        return (ObservableTransformer<T, T>) mainSchedulersTransformer;
    }

    public static <T> ObservableTransformer<T, T> applyMainIOSchedulers() {
        return (ObservableTransformer<T, T>) schedulersMainIOTransformer;
    }

}
