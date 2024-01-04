package com.example.checkinventory.modelo;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application { //Clase para poder obtener el Context de forma estática

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
