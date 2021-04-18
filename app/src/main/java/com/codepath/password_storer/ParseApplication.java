package com.codepath.password_storer;

import android.app.Application;
import android.net.Credentials;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Credential.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("I5d9ZVJQSmeiroNMOrEcXevMFfQKtUvX1McqFloh")
                .clientKey("KGpKZCR4ARYnjfewbt7WuM13wlgXApSFl5ei1y6m")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
