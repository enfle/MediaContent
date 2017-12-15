package com.km2labs.mediacontent.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by : Subham Tyagi
 * Created on :  28/08/16.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
