package com.km2labs.mediacontent.common.beans.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateSessionDto {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("session_id")
    @Expose
    private String sessionId;

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
     * @return The sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId The session_id
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}