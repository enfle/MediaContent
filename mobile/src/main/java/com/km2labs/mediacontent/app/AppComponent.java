package com.km2labs.mediacontent.app;

import com.km2labs.mediacontent.dagger.ActivityBindingModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBindingModule.class
})
public interface AppComponent {
    App inject(App app);
}
