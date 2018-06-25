package com.recipey.nhnic.recipey.app;

/**
 * Created by nhnic on 5/11/2018.
 */

public class Application extends android.app.Application {
    private static Application instance;
    public static Application getInstance() {return instance;}

    public Application() {
        instance = this;
    }
}
