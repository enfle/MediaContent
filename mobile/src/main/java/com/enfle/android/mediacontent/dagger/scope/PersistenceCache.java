package com.enfle.android.mediacontent.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by : Subham Tyagi
 * Created on :  02/09/16.
 */

@Qualifier()
@Documented
@Retention(RUNTIME)
public @interface PersistenceCache {
}
