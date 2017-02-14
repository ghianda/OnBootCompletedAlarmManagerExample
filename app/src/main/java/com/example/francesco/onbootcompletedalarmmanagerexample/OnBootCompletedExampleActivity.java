package com.example.francesco.onbootcompletedalarmmanagerexample;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class OnBootCompletedExampleActivity extends AppCompatActivity {



    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boot_completed_example);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Clear the Notification Bar after you've clicked on the message in the Notification Bar
        NotificationManager nMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nMgr.cancelAll();
    }



    public void onClickStopAlarm(View view){
        // stop the alarm in background
        //here i recreate the pendingIntent that start the alarm and cancel it with alarm manager

        Intent i = new Intent(getApplicationContext(), NotificationBarAlarm.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        final PendingIntent piToStop = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(piToStop);
        Log.i("onClickStopAlarm", "end schedler alarm");

    }


    public void onClickStartAlarm(View view){
        //restart the alarm in background that repeat the task

        Intent i = new Intent(this, NotificationBarAlarm.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        // Repeat the notification every 15 seconds (15000)
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000, pi);

        Toast.makeText(this, "My Service RE-started", Toast.LENGTH_LONG).show();
    }
}
