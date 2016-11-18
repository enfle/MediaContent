package com.km2labs.mediacontent.dagger.core.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.km2labs.mediacontent.app.App;

/**
 * Created by : Subham Tyagi
 * Created on :  29/08/16.
 */

public abstract class DaggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
    }

    protected void setupActivityComponent() {
        injectMembers(App.get(this));
    }

    protected abstract void injectMembers(ActivitySubcomponentBuilders activitySubcomponentBuilders);
}
