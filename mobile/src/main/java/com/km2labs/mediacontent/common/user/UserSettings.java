package com.km2labs.mediacontent.common.user;

/**
 * Created by : Subham Tyagi
 * Created on :  04/10/16.
 */

public class UserSettings {

    private static final UserSettings USER_SETTINGS = new UserSettings();
    private User mUser;

    public static UserSettings getUserSettings() {
        return USER_SETTINGS;
    }

    public User getUser() {
        return mUser;
    }
}
