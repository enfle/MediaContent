package com.enfle.android.mediacontent.app;


import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Override
    void inject(App instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(App application);

        AppComponent build();
    }
}
