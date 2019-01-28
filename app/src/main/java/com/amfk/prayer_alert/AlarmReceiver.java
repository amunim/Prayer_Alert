package com.amfk.prayer_alert;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    private PowerManager.WakeLock wl;

    @Override
    public void onReceive(Context context, Intent intent) {

        String state = intent.getExtras().getString("Prayer");

        Toast.makeText(context,"Prayer Time " + state + " Started",Toast.LENGTH_LONG).show();
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "prayer:" + state);
        wl.acquire();
        //add code to do something when alarm goes off
        Notify(context, state);

        context.startActivity(new Intent(context,AlarmRingActivity.class).putExtra("Prayer",state));
        wl.release();
    }

    private void Notify(Context context, String prayer){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, "Prayer")
                        .setSmallIcon(R.drawable.notification_flat)
                        .setContentTitle(prayer + " Time STARTED!!!")   //this is the title of notification
                        .setContentText("Prayer time of " + prayer + " has started")   //this is the message showed in notification
                        .setPriority(NotificationCompat.PRIORITY_MAX);
        Intent mintent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mintent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        // Add as notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
