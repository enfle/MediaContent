package com.km2labs.mediacontent.dagger.core.ui.activity;

import com.km2labs.mediacontent.dagger.core.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ActivityModule<T> {

    protected final T activity;

    public ActivityModule(T activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public T provideActivity() {
        return activity;
    }
}