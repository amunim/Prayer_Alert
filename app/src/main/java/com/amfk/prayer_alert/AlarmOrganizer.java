package com.amfk.prayer_alert;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmOrganizer {
    private static PendingIntent pendingIntent;
    private static Context MainContext;

    public static void SetAlarm(Namaaz namaaz, Context context){
        //Retrieve a pending intent that will perform the broadcast
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        alarmIntent.putExtra("prayer","Dhuhr");
        pendingIntent = PendingIntent.getBroadcast(context,0,alarmIntent,0);
        MainContext = context;

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(System.currentTimeMillis());
        //calendar.set(Calendar.HOUR_OF_DAY, TIME OF DHUR);
        calendar.add(Calendar.MINUTE, 1);

        //set alarm
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 8000, pendingIntent);
        //Toast.makeText(context,"alarm set",Toast.LENGTH_LONG).show();
    }

    public static void CancelAlarm(){
        AlarmManager manager = (AlarmManager) MainContext.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(MainContext,"Alarm Canceled", Toast.LENGTH_LONG).show();
    }
}
