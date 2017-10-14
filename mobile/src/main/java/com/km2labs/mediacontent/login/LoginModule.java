package com.km2labs.mediacontent.login;

import com.km2labs.mediacontent.core.util.RetrofitHelper;
import com.km2labs.mediacontent.dagger.core.scope.ActivityScope;
import com.km2labs.mediacontent.dagger.core.ui.activity.AbsModule;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger login module to provide login related dependency.
 */
@Module
class LoginModule extends AbsModule<LoginFragment> {
    /**
     * Provide dependency for {@link LoginPresenter}.
     *
     * @return - {@link LoginPresenter}
     */

    @Provides
    @ActivityScope
    LoginService provideLoginService() {
        return RetrofitHelper.getRetrofitHelper().createService(LoginService.class);
    }
}