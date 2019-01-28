package com.amfk.prayer_alert;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlarmOrganizer {
    private static Context MainContext;

    public static void SetAlarm(Namaaz namaz, Context context) {
        MainContext = context;

        CancelAlarm();

        Calendar[] calendar = DecodeNamazTimings(namaz);
        SharedPreferences.Editor prefs = MainContext.getSharedPreferences("User", Context.MODE_PRIVATE).edit();

        for (int i = 0; i < 5; i++) {
            prefs.putLong(Prayers.Names[i], calendar[i].getTimeInMillis());
        }
        prefs.apply();

        PendingIntent pendingIntent = null;
        Intent intent = new Intent(MainContext, AlarmReceiver.class);

        Calendar now = Calendar.getInstance();

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        for (int i = 0; i < 5; i++) {
            if (!calendar[i].after(now)){
               calendar[i].add(Calendar.DAY_OF_MONTH,1 );
            }
            intent.putExtra("Prayer",Prayers.Names[i]);

            pendingIntent = PendingIntent.getBroadcast(MainContext, i, intent, 0);
            Toast.makeText(context,"name : " + intent.getExtras().getString("Prayer") + " ||| Timings : " + calendar[i].get(Calendar.HOUR_OF_DAY) + " "+ calendar[i].get(Calendar.MINUTE) + " || DATE : " + calendar[i].get(Calendar.DAY_OF_MONTH),Toast.LENGTH_LONG).show();

            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar[i].getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
        }
    }

    public static void CancelAlarm() {
        AlarmManager manager = (AlarmManager) MainContext.getSystemService(Context.ALARM_SERVICE);

        for (int i = 0; i < 5; i++) {
            Intent intent = new Intent(MainContext, AlarmReceiver.class);
            intent.putExtra("Prayer",Prayers.Names[i]);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainContext, i, intent, 0);
            manager.cancel(pendingIntent);
        }
    }

    private static Calendar[] DecodeNamazTimings(Namaaz object) {
        Calendar[] cals = new Calendar[5];
        cals[0] = DecodeNamazTime(object.data.timings.fajr);
        cals[1] = DecodeNamazTime(object.data.timings.dhuhr);
        cals[2] = DecodeNamazTime(object.data.timings.asr);
        cals[3] = DecodeNamazTime(object.data.timings.maghrib);
        cals[4] = DecodeNamazTime(object.data.timings.isha);

        return cals;
    }

    private static Calendar DecodeNamazTime(String time) {
        Calendar cal = Calendar.getInstance();
        Calendar now = (Calendar) cal.clone();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        cal.set(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));

        return cal;
    }
}
