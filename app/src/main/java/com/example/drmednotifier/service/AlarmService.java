package com.example.drmednotifier.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.drmednotifier.R;
import com.example.drmednotifier.RingActivity;

import static com.example.drmednotifier.application.App.CHANNEL_ID;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.MED_DOSE;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.TITLE;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.MED_NAME;
//import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.MED_DOSE;

public class AlarmService extends Service {
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
        mediaPlayer.setLooping(true);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("myTag", "ALARM STARTED");

        Intent notificationIntent = new Intent(this, RingActivity.class);
        notificationIntent.putExtra(TITLE, intent.getStringExtra(TITLE));
        notificationIntent.putExtra(MED_NAME, intent.getStringExtra(MED_NAME));
        notificationIntent.putExtra(MED_DOSE, intent.getIntExtra(MED_DOSE, 0));

        Log.d("myTag", String.format("NOTI NAME: %s", intent.getStringExtra(MED_NAME)));

        Log.d("myTag", String.format("NOTI DOSE: %d", intent.getIntExtra(MED_DOSE, 0)));

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        String alarmTitle = intent.getStringExtra(TITLE);

        Log.d("myTag", "TITLE: " + alarmTitle);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(alarmTitle)
                .setContentText("Please take your medication!")
                .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                .setContentIntent(pendingIntent)
                .build();

        mediaPlayer.start();

        long[] pattern = { 0, 100, 1000 };
        vibrator.vibrate(pattern, 0);

        startForeground(1, notification);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
        vibrator.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
