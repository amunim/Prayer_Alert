package com.amfk.prayer_alert;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

            String state = intent.getExtras().getString("prayer");

            Toast.makeText(context,"Prayer Time Started",Toast.LENGTH_LONG).show();
    }
}
