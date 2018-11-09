package com.capriccioso.groceryapp.application;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by dobit on 11/9/2018.
 */

public class AndroidApplication extends Application {


    //Initialize Realm just once per application
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
