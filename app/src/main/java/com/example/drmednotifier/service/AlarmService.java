package com.example.drmednotifier.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.drmednotifier.R;
import com.example.drmednotifier.RingActivity;
import com.example.drmednotifier.data.NotifSetting;
import com.example.drmednotifier.data.NotifSettingDao;
import com.example.drmednotifier.data.NotifSettingDatabase;

import static com.example.drmednotifier.application.App.CHANNEL_ID;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.MED_DOSE;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.MED_ID;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.MED_NAME;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.TITLE;

public class AlarmService extends Service {
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;

    private NotifSettingDatabase notifSettingDatabase;
    private NotifSettingDao notifSettingDao;
    private NotifSetting notifSetting;

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
        mediaPlayer.setLooping(true);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        notifSettingDatabase = NotifSettingDatabase.getDatabase(this);
        notifSettingDao = notifSettingDatabase.notifSettingDao();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notifSetting = notifSettingDao.getNotifSettings().get(0);

        Log.d("myTag", "ALARM STARTED");

        Intent notificationIntent = new Intent(this, RingActivity.class);
        int medId = intent.getIntExtra(MED_ID, 1);
        notificationIntent.putExtra(MED_ID, medId);
        notificationIntent.putExtra(TITLE, intent.getStringExtra(TITLE));
        notificationIntent.putExtra(MED_NAME, intent.getStringExtra(MED_NAME));
        notificationIntent.putExtra(MED_DOSE, intent.getIntExtra(MED_DOSE, 0));

        Log.d("myTag", String.format("NOTIF NAME: %s", intent.getStringExtra(MED_NAME)));

        Log.d("myTag", String.format("NOTIF DOSE: %d", intent.getIntExtra(MED_DOSE, 0)));

        PendingIntent pendingIntent = PendingIntent.getActivity(this, medId, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        String alarmTitle = intent.getStringExtra(TITLE);

        Log.d("myTag", "TITLE: " + alarmTitle);

        String notifMessage = notifSetting.getNotifMessage();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(alarmTitle)
                .setContentText(notifMessage)
                .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                .setContentIntent(pendingIntent)
                .build();

        int notifTypeId = notifSetting.getNotifTypeId();

        if (notifTypeId >= 2) {
            mediaPlayer.start();
        }

        if (notifTypeId >= 1) {
            long[] pattern = {0, 100, 1000};
            vibrator.vibrate(pattern, 0);
        }

        CountDownTimer timer = new CountDownTimer(60000, 60) {

            @Override
            public void onTick(long millisUntilFinished) {
                // Nothing to do
            }

            @Override
            public void onFinish() {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                vibrator.cancel();
            }
        };
        timer.start();

        if (isServiceRunningInForeground(this, AlarmService.class)) {
            NotificationManager manager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(medId, notification);
        } else {
            startForeground(medId, notification);
        }

        Log.d("myTag", "NOTIF_ID: " + medId);

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

    public static boolean isServiceRunningInForeground(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                if (service.foreground) {
                    return true;
                }

            }
        }
        return false;
    }
}
