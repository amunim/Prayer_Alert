package com.amfk.prayer_alert;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static Context MainContext;

    private static EditText Fajr;
    private static EditText Dhur;
    private static EditText Asr;
    private static EditText Maghrib;
    private static EditText Isha;

    private static Namaaz Timings;
    private static ProgressDialog dialog;

    public static void SetTimings(Namaaz namaaz) {
        Fajr.setText(namaaz.data.timings.fajr);
        Dhur.setText(namaaz.data.timings.dhuhr);
        Asr.setText(namaaz.data.timings.asr);
        Maghrib.setText(namaaz.data.timings.maghrib);
        Isha.setText(namaaz.data.timings.isha);

        Timings = namaaz;

        ProgressDialogVisiblity(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetTextReferences();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(
                    Context.NOTIFICATION_SERVICE);
            NotificationChannel infoChannel = new NotificationChannel("Prayer",
                    "Prayer", NotificationManager.IMPORTANCE_DEFAULT);
            infoChannel.setDescription("Prayer Time Started");
            infoChannel.enableLights(false);
            infoChannel.enableVibration(false);
            mNotificationManager.createNotificationChannel(infoChannel);
        }

        SharedPreferences prefs = getSharedPreferences("User",MODE_PRIVATE);
        if (!prefs.getBoolean("Custom",false)){
            OnRefreshBTNClick(null);
        }
    }

    private void SetTextReferences() {
        Fajr = (EditText) findViewById(R.id.FajrTime);
        Dhur = (EditText) findViewById(R.id.DhurTime);
        Asr = (EditText) findViewById(R.id.AsrTime);
        Maghrib = (EditText) findViewById(R.id.MaghribTime);
        Isha = (EditText) findViewById(R.id.IshaTime);

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Downloading Current Timings, Please Wait");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);

        MainContext = MainActivity.this;
    }

    public void OnRefreshBTNClick(View v) {
        if (!InternetAvailiblity.isNetworkConnected(MainActivity.this)) {
            return;
        }
        ProgressDialogVisiblity(true);
        //get city and country
        PrayerTimings.Start();
    }

    public void OnSaveBTNClick(View v) {
        //Set alarms of the timings
        AlarmOrganizer.SetAlarm(Timings, MainActivity.this);
    }

    private static void ProgressDialogVisiblity(boolean show){
        String msg = MainContext.getString(R.string.progress_loader_dialog);

        ProgressDialogVisiblity(show, msg);
    }

    private static void ProgressDialogVisiblity(boolean show, String msg){
        if (show){
            dialog.setMessage(msg);
            dialog.show();
        }else{
            dialog.hide();
        }
    }
}
