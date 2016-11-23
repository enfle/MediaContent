package com.km2labs.mediacontent.loaders.core;

import com.km2labs.framework.mvp.IPresenter;

public interface PresenterFactory<T extends IPresenter> {
    T create();
}