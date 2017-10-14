package com.km2labs.mediacontent.dagger.core.ui.activity;

import android.app.Activity;

import com.km2labs.mediacontent.dagger.core.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class AbsModule<T> {

    @Provides
    @ActivityScope
    public T provideActivity(Activity activity) {
        return (T) activity;
    }
}