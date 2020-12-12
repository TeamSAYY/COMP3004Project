package com.example.drmednotifier;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drmednotifier.data.GenerateRandomInt;
import com.example.drmednotifier.data.Medication;
import com.example.drmednotifier.data.NotifSetting;
import com.example.drmednotifier.data.NotifSettingDao;
import com.example.drmednotifier.data.NotifSettingDatabase;
import com.example.drmednotifier.service.AlarmService;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.MED_DOSE;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.MED_ID;
import static com.example.drmednotifier.broadcastreceiver.AlarmBroadcastReceiver.MED_NAME;

public class RingActivity extends AppCompatActivity {
    @BindView(R.id.activity_ring_dismiss)
    protected Button dismiss;
    @BindView(R.id.activity_ring_snooze)
    protected Button snooze;
    @BindView(R.id.activity_ring_clock)
    protected ImageView clock;
    @BindView(R.id.textView)
    protected TextView message;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);

        Context context = this;

        ButterKnife.bind(this);

        NotifSettingDatabase notifSettingDatabase = NotifSettingDatabase.getDatabase(this);
        NotifSettingDao notifSettingDao = notifSettingDatabase.notifSettingDao();
        NotifSetting notifSetting = notifSettingDao.getNotifSettings().get(0);

        int medID = getIntent().getIntExtra(MED_ID, 0);
        String medName = getIntent().getStringExtra(MED_NAME);
        int medDose = getIntent().getIntExtra(MED_DOSE, 0);

        message.setText(String.format("Remember to take %d %s", medDose, medName));

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                int len0 = manager.getActiveNotifications().length;
                manager.cancel(medID);
                int len1 = manager.getActiveNotifications().length;
                if (len0 == len1) { // this is the last active notification, stop the foreground service
                    Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
                    getApplicationContext().stopService(intentService);
                }
                Intent i = new Intent(context, Dose_Page.class);
                startActivity(i);
                finish();
            }
        });

        int snoozeMinutes = notifSetting.getSnoozeMinutes();
        if (snoozeMinutes > 0) {
            snooze.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.add(Calendar.MINUTE, snoozeMinutes);

                    Medication medication = new Medication(
                            GenerateRandomInt.get(),
                            medName,
                            "",
                            0,
                            0,
                            System.currentTimeMillis(),
                            true,
                            true,
                            true,
                            true,
                            true,
                            true,
                            true,
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            medDose
                    );

                    medication.schedule(getApplicationContext());

                    NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    int len0 = manager.getActiveNotifications().length;
                    manager.cancel(medID);
                    int len1 = manager.getActiveNotifications().length;
                    if (len0 == len1) {
                        Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
                        getApplicationContext().stopService(intentService);
                    }
                    finish();
                }
            });
        } else {
            snooze.setVisibility(View.GONE);
        }

        animateClock();
    }

    private void animateClock() {
        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(clock, "rotation", 0f, 20f, 0f, -20f, 0f);
        rotateAnimation.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimation.setDuration(800);
        rotateAnimation.start();
    }
}
