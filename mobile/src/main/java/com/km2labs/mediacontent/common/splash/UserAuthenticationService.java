package com.km2labs.mediacontent.common.splash;

import com.km2labs.mediacontent.common.user.bean.GuestUserLoginResponseDTO;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public interface UserAuthenticationService {

    @GET("authentication/guest_session/new")
    Observable<GuestUserLoginResponseDTO> guestLogin();

}
