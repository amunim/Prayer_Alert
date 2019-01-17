package com.amfk.prayer_alert;

import java.io.IOException;
import java.net.*;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static EditText Fajr;
    private static EditText Dhur;
    private static EditText Asr;
    private static EditText Maghrib;
    private static EditText Isha;

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
    }
}
