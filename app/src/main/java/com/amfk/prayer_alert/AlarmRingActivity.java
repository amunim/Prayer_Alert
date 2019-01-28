package com.amfk.prayer_alert;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Arrays;
import java.util.Calendar;

public class AlarmRingActivity extends AppCompatActivity {

    public String PrayerName;

    private EditText NumberDisplay;
    private EditText NumberInput;
    private String RandomNumber;

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_ring);

        PrayerName = getIntent().getExtras().getString("Prayer");

        if (PrayerName.equals("Fajr")) {
            player = MediaPlayer.create(AlarmRingActivity.this,R.raw.islamic_alarm_tone);
        } else {
            player = MediaPlayer.create(AlarmRingActivity.this,R.raw.azan_takber_alkatamy);
        }
        player.setLooping(true);

        SetReferences();

        RefreshCaptcha();

    }

    private void SetReferences(){
        NumberDisplay = findViewById(R.id.NumberDisplay);
        NumberInput = findViewById(R.id.NumberInput);
    }

    public void OnCloseBTNClick(View v){
        if(!NumberInput.getText().toString().replaceAll(" ","").equals(RandomNumber)){
            OnIncorrectCaptchaAnswer();
        }else{
            player.stop();
            this.finish();
        }
    }

    public void OnSnoozeBTNClick(View v){
        if(!NumberInput.getText().toString().replaceAll(" ","").equals(RandomNumber)){
            OnIncorrectCaptchaAnswer();
        }else{
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, 5);

            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(AlarmRingActivity.this, AlarmReceiver.class);
            intent.putExtra("Prayer",PrayerName);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmRingActivity.this,GetRequestCode(PrayerName),intent,0);

            manager.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pendingIntent);
            finish();
            player.stop();
        }
    }

    private int GetRequestCode(String string){
        return Arrays.asList(Prayers.Names).indexOf(string);
    }

    private void RefreshCaptcha(){
        RandomNumber = Integer.toString((int)(Math.random() * 5000 + 1000));

        NumberDisplay.setText(RandomNumber);
        NumberInput.setText("");
    }

    private void OnIncorrectCaptchaAnswer(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please enter a correct captcha!!!")
                .setTitle("Incorrect Captcha");
        builder.show();

        RefreshCaptcha();
    }
}
