package com.amfk.prayer_alert;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class DeviceBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETE")){
            SharedPreferences prefs = context.getSharedPreferences("User", Context.MODE_PRIVATE);

            Long[] alarms = new Long[5];
            for (int i = 0; i < 5; i++) {
                alarms[i] = prefs.getLong(Prayers.Names[i],0);
            }

            PendingIntent pendingIntent = null;
            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            for (int i = 0; i < 5; i++) {
                if (AlarmTimePassed(alarms[i])){
                    continue;
                }

                intent.putExtra("Prayer",Prayers.Names[i]);

                pendingIntent = PendingIntent.getBroadcast(context, i, intent, 0);
                manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarms[i], AlarmManager.INTERVAL_DAY,pendingIntent);
            }
        }
    }

    private static boolean AlarmTimePassed(Long millis){
        return millis < System.currentTimeMillis();
    }
}
