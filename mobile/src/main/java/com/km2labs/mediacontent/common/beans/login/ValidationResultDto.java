package com.km2labs.mediacontent.common.beans.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidationResultDto {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("request_token")
    @Expose
    private String requestToken;

    /**
     * @return The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return The requestToken
     */
    public String getRequestToken() {
        return requestToken;
    }

    /**
     * @param requestToken The request_token
     */
    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

}