package com.example.habitimia.util;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.habitimia.R;

public class MyNotification {

    public static void createNotification( Activity activity, Context context){
        String title = "Habitimia";
        String content =  "Check out your tasks for today !";
        createHighImportanceChannel(title, content, activity, context);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private static NotificationChannel createHighImportanceChannel(Activity activity){
        /*NotificationChannel is necessary Since Oreo*/

        NotificationChannel channel = new NotificationChannel("UrgentNotification", "UrgentNotification", NotificationManager.IMPORTANCE_HIGH);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        channel.setSound(alarmSound, new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build());
        NotificationManager manager = activity.getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        return channel;
    }

    private static void createHighImportanceChannel(String title, String content, Activity activity, Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = createHighImportanceChannel(activity);
        }

        Intent openAppIntent = new Intent(context, activity.getClass());
        openAppIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, openAppIntent, PendingIntent.FLAG_IMMUTABLE);
        PendingIntent firstActionPendingIntent = PendingIntent.getActivity(context, 0, openAppIntent, PendingIntent.FLAG_IMMUTABLE);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity, "UrgentNotification");
        builder.setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.logo)
                .setAutoCancel(true)
                .setCategory(Notification.CATEGORY_ALARM)
                .setDefaults((Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND | Notification.FLAG_SHOW_LIGHTS))
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.logo, "Open App", firstActionPendingIntent);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(activity);

        /*Send the notification*/
        managerCompat.notify(1, builder.build());
    }



}
