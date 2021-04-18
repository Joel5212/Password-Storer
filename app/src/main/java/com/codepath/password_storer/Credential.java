package com.codepath.password_storer;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;


@ParseClassName("Credential")
public class Credential extends ParseObject {

    public static final String KEY_WEBSITE_NAME = "WebsiteName";

    public static final String KEY_SAVED_PASSWORD = "SavedPassword";

    public static final String KEY_SAVED_USERNAME = "SavedUsername";

    public static final String KEY_USER = "user";

    public static final String KEY_URL = "URL";

    public static final String KEY_CREATED_KEY = "createdAt";



    public String getWebsiteName()
    {
        return getString(KEY_WEBSITE_NAME);
    }

    public void setWebsiteName(String WebsiteName)
    {
        put(KEY_WEBSITE_NAME, WebsiteName);
    }

    public String getSavedPassword()
    {
        return getString(KEY_SAVED_PASSWORD);
    }

    public void setSavedPassword(String savedPassword)
    {
        put(KEY_SAVED_PASSWORD, savedPassword);
    }

    public ParseUser getUser()
    {

        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user)
    {
        put(KEY_USER, user);
    }

    public String getURL()
    {
        return getString(KEY_URL);
    }

    public void setURL(String url)
    {
        put(KEY_URL, url);
    }

    public String getSavedUsername()
    {
        return getString(KEY_SAVED_USERNAME);
    }

    public void setSavedUsername(String savedUsername)
    {
        put(KEY_SAVED_USERNAME, savedUsername);
    }

}
