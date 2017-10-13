package com.km2labs.mediacontent.app;

import android.app.Activity;
import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.core.BuildConfig;
import com.crashlytics.android.core.CrashlyticsCore;
import com.flurry.android.FlurryAgent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);

        CrashlyticsCore core = new CrashlyticsCore.Builder()
                //.disabled(BuildConfig.DEBUG)
                .build();

        FlurryAgent.setLogEnabled(false);
        FlurryAgent.init(this, "5CRSYGMX6ZVP56538W8J");
        Fabric.with(this, new Crashlytics.Builder().core(core).build(), new Answers());
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
