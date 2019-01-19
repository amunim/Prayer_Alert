package com.amfk.prayer_alert;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static EditText Fajr;
    private static EditText Dhur;
    private static EditText Asr;
    private static EditText Maghrib;
    private static EditText Isha;

    private static Namaaz Timings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetTextReferences();
    }

    public static void SetTimings(Namaaz namaaz) {
        Fajr.setText(namaaz.data.timings.fajr);
        Dhur.setText(namaaz.data.timings.dhuhr);
        Asr.setText(namaaz.data.timings.asr);
        Maghrib.setText(namaaz.data.timings.maghrib);
        Isha.setText(namaaz.data.timings.isha);

        Timings = namaaz;
    }

    private void SetTextReferences(){
        Fajr = (EditText) findViewById(R.id.FajrTime);
        Dhur = (EditText) findViewById(R.id.DhurTime);
        Asr = (EditText) findViewById(R.id.AsrTime);
        Maghrib = (EditText) findViewById(R.id.MaghribTime);
        Isha = (EditText) findViewById(R.id.IshaTime);
    }

    public void OnRefreshBTNClick(View v){
        if (!InternetAvailiblity.isNetworkConnected(MainActivity.this)) {
            return;
        }
        //get city and country
        PrayerTimings.Start();
    }

    public void OnSaveBTNClick(View v){
        if (!InternetAvailiblity.isNetworkConnected(MainActivity.this)) {
            return;
        }

        //Set alarms of the timings
        SetAlarm(Timings);
    }

    private void SetAlarm(Namaaz namaaz){
        //Retrieve a pending intent that will perform the broadcast
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,alarmIntent,0);

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        //calendar.set(Calendar.HOUR_OF_DAY, TIME OF DHUR);
        calendar.add(Calendar.MINUTE, 1);

        //set alarm
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 8000, pendingIntent);
    }
}
