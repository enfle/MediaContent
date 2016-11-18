package com.km2labs.mediacontent.loaders.core;

import com.km2labs.mediacontent.common.ui.mvp.IPresenter;

public interface PresenterFactory<T extends IPresenter> {
    T create();
}