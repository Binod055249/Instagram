package com.example.instagram;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("jMIjKZBcJUJZKfpg7kCl4TfYLUoCR4kcZD04bJ8l")
                .clientKey("WgjLTXrD7QMLN5kpWZXR1w1nd02pd2RNawdQ61oK")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
