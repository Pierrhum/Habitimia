package com.example.habitimia.util;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NotificationReceiver extends Service {
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    public NotificationReceiver () {
    }


    @Override
    public IBinder onBind (Intent intent) {
        System.out.println("notif");
        throw new UnsupportedOperationException( "Not yet implemented" ) ;
    }
}