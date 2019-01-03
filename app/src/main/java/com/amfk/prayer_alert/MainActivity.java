package com.amfk.prayer_alert;

import java.net.*;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    private static final String IPSTACK_KEY = "7d4df146a6335f13540003cba6cb5da7";
    private static final String IPSTACK_Endpoint = "http://api.ipstack.com/";

    private static final String PRAYER_TIMING_ENDPOINT = "http://api.aladhan.com/v1/timingsByCity";
    private final EditText Fajr = findViewById(R.id.FajrTime);
    private final EditText Dhur = findViewById(R.id.DhurTime);
    private final EditText Asr = findViewById(R.id.AsrTime);
    private final EditText Maghrib = findViewById(R.id.MaghribTime);
    private final EditText Isha = findViewById(R.id.IshaTime);
    private boolean gettingLocation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Gson gson = new Gson();

        if (gettingLocation) {
            gettingLocation = false;
            IPStack stack = new IPStack();
            gson.fromJson(output, stack.getClass());

            GetTimings(stack);
        } else {
            Namaaz namaaz = new Namaaz();
            gson.fromJson(output, namaaz.getClass());

            SetTimings(namaaz);
        }
    }

    public void OnIPStackObtained(IPStack stack) {

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

