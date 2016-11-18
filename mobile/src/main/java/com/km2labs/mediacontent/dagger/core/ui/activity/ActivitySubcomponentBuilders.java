package com.km2labs.mediacontent.dagger.core.ui.activity;

import android.app.Activity;


public interface ActivitySubcomponentBuilders {
    <T extends ActivityComponentBuilder> T getActivityComponentBuilder(Class<? extends Activity> activityClass);
}