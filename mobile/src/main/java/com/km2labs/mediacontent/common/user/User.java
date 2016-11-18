package com.km2labs.mediacontent.common.user;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class User {

    UserType mUserType;
    String mName;
    String mUserName;
    String mSessionToken;

    public UserType getUserType() {
        return mUserType;
    }

    public void setUserType(UserType userType) {
        mUserType = userType;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getSessionToken() {
        return mSessionToken;
    }

    public void setSessionToken(String sessionToken) {
        mSessionToken = sessionToken;
    }
}
