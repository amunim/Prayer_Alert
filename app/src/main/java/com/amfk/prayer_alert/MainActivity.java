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

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    private static final String IPSTACK_KEY = "7d4df146a6335f13540003cba6cb5da7";
    private static final String IPSTACK_Endpoint = "http://api.ipstack.com/";

    private static final String PRAYER_TIMING_ENDPOINT = "http://api.aladhan.com/v1/timingsByCity";
    private EditText Fajr;
    private EditText Dhur;
    private EditText Asr;
    private EditText Maghrib;
    private EditText Isha;
    private boolean gettingLocation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fajr = findViewById(R.id.FajrTime);
        Dhur = findViewById(R.id.DhurTime);
        Asr = findViewById(R.id.AsrTime);
        Maghrib = findViewById(R.id.MaghribTime);
        Isha = findViewById(R.id.IshaTime);

        findViewById(R.id.RefreshBTN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkConnected()) {
                    return;
                }
                //get city and country
                GetCityCountry();
            }
        });
    }

    //this override the implemented method from asyncTask
    @Override
    public void processFinish(String output) {
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
        Gson gson = new GsonBuilder().create();

        if (gettingLocation) {
            gettingLocation = false;
            IPStack stack = gson.fromJson(output, IPStack.class);

            GetTimings(stack);
        } else {
            Namaaz namaaz = new Namaaz();
            gson.fromJson(output, namaaz.getClass());

            SetTimings(namaaz);
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    private void GetCityCountry() {
        String Url = IPSTACK_Endpoint + "check?access_key=" + IPSTACK_KEY;
        GetJSONString getIPStackJSON = new GetJSONString();
        gettingLocation = true;
        getIPStackJSON.delegate = this;
        getIPStackJSON.execute(Url);
    }

    private void GetTimings(IPStack ipStack) {
        String Url = PRAYER_TIMING_ENDPOINT + "?city=" + ipStack.city + "&country=" + ipStack.countryCode;
        GetJSONString getIPStackJSON = new GetJSONString();
        gettingLocation = false;
        getIPStackJSON.delegate = this;
        getIPStackJSON.execute(Url);
    }

    private void SetTimings(Namaaz namaaz) {
        Fajr.setText(namaaz.data.timings.fajr);
        Dhur.setText(namaaz.data.timings.dhuhr);
        Asr.setText(namaaz.data.timings.asr);
        Maghrib.setText(namaaz.data.timings.maghrib);
        Isha.setText(namaaz.data.timings.isha);
    }
}

