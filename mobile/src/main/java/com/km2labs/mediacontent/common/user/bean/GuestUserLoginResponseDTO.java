package com.km2labs.mediacontent.common.user.bean;


import com.google.gson.annotations.SerializedName;

public class GuestUserLoginResponseDTO {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("guest_session_id")
    private String guestSessionId;
    @SerializedName("expires_at")
    private String expiresAt;


    public Boolean getSuccess() {
        return success;
    }


    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public String getGuestSessionId() {
        return guestSessionId;
    }


    public void setGuestSessionId(String guestSessionId) {
        this.guestSessionId = guestSessionId;
    }


    public String getExpiresAt() {
        return expiresAt;
    }


    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

}