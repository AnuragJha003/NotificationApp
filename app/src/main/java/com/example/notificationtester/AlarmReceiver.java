package com.example.notificationtester;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //GET ID AND MESSAGE FROM INTENT
        int notificationid=intent.getIntExtra("notificationid",0);
        String message=intent.getStringExtra("todo");

        //when intention tapped call mainactiviyy
        Intent mainIntent=new Intent(context,MainActivity.class);
        PendingIntent contentIntent=PendingIntent.getActivity(context,0,mainIntent,0);

        NotificationManager myNotificationManager=(NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        //PREPARE NOTIFICATION
        Notification.Builder builder=new Notification.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("HELLO THERE IT'S ANU THIS SIDE")
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent);

        //NOTIFY
        myNotificationManager.notify(notificationid, builder.build());
    }
}
