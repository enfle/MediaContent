package com.km2labs.mediacontent.dagger.core.scope;

import android.app.Activity;

import dagger.MapKey;

/**
 * Created by froger_mcs on 16/10/2016.
 */

@MapKey
public @interface ActivityKey {
    Class<? extends Activity> value();
}