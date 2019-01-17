package com.amfk.prayer_alert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PrayerTimings implements AsyncResponse{
    private static final String IPSTACK_KEY = "7d4df146a6335f13540003cba6cb5da7";
    private static final String IPSTACK_Endpoint = "http://api.ipstack.com/";

    private static final String PRAYER_TIMING_ENDPOINT = "http://api.aladhan.com/v1/timingsByCity";

    private static boolean gettingLocation;

    public static void Start() {
        new PrayerTimings().GetCityCountry();

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
            Namaaz namaaz = gson.fromJson(output, Namaaz.class);

            MainActivity.SetTimings(namaaz);
        }
    }
}
