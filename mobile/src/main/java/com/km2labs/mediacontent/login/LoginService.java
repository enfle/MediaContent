package com.km2labs.mediacontent.login;

import com.km2labs.mediacontent.common.beans.login.CreateSessionDto;
import com.km2labs.mediacontent.common.beans.login.RequestTokenDTO;
import com.km2labs.mediacontent.common.beans.login.ValidationResultDto;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by subhamtyagi on 14/12/16.
 */

public interface LoginService {

    @GET("/authentication/token/new")
    Observable<RequestTokenDTO> getRequestToken();

    @GET("/authentication/token/validate_with_login")
    Observable<ValidationResultDto> validateRequestToken(@Path("username") String userName, @Path("password")
            String password, @Path("request_token") String requestToken);

    @GET("/authentication/session/new")
    Observable<CreateSessionDto> createSession(@Path("request_token") String requestToken);
}
