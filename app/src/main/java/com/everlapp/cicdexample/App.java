package com.everlapp.cicdexample;

import android.app.Application;

import com.everlapp.cicdexample.di.ApplicationComponent;
import com.everlapp.cicdexample.di.ContextModule;
import com.everlapp.cicdexample.di.DaggerApplicationComponent;


public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }


    public ApplicationComponent getComponent() {
        return component;
    }

}
