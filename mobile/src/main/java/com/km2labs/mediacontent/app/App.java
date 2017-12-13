package com.km2labs.mediacontent.app;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */


public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().application(this).build().inject(this);
        extera();
    }

    private void extera() {
        //
//        CrashlyticsCore core = new CrashlyticsCore.Builder()
//                .disabled(BuildConfig.DEBUG)
//                .build();
//        FlurryAgent.setLogEnabled(false);
//        FlurryAgent.init(this, "5CRSYGMX6ZVP56538W8J");
//        Fabric.with(this, new Crashlytics.Builder().core(core).build(), new Answers());
//        if (BuildConfig.DEBUG) {
//            Timber.plant(new Timber.DebugTree());
//        }
//        //Timber.plant(new CrashlyticsTree());
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
