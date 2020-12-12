package com.example.drmednotifier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drmednotifier.data.NotifSetting;
import com.example.drmednotifier.data.NotifSettingDao;
import com.example.drmednotifier.data.NotifSettingDatabase;
import com.example.drmednotifier.data.User;
import com.example.drmednotifier.data.UserDao;
import com.example.drmednotifier.data.UserDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*load animation*/
        Animation topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anime);
        Animation bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anime);

        ImageView text = findViewById(R.id.logotext);
        TextView logo = findViewById(R.id.welcome);
        Button TB1 = findViewById(R.id.Tbutton1);

        /*insert animation*/
        text.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        TB1.setAnimation(bottomAnim);

        initNotifSettings();
    }

    public void launchActivity(View x){
        if (existingUserInfo()) {
            Intent i = new Intent(this, Nav_page.class);
            startActivity(i);
        }
        else {
            Intent i = new Intent(this, Second_page_get_personaldata.class);
            startActivity(i);
        }
    }

    private boolean existingUserInfo() {
        UserDatabase userDatabase = UserDatabase.getDatabase(getApplicationContext());
        UserDao userDao = userDatabase.userDao();
        List<User> usersLiveData = userDao.getUser();

        if (!usersLiveData.isEmpty()) {
            // If the avatar is set but no other information saved, still show the second page
            return usersLiveData.get(0).getFirstName().length() != 0;
        } else {
            return false;
        }
    }

    private void initNotifSettings() {
        NotifSettingDatabase notifSettingDatabase = NotifSettingDatabase.getDatabase(this);
        NotifSettingDao notifSettingDao = notifSettingDatabase.notifSettingDao();
        List<NotifSetting> notifSettings = notifSettingDao.getNotifSettings();

        if (notifSettings.isEmpty()) {
            NotifSetting notifSetting = new NotifSetting();
            notifSettingDao.insert(notifSetting);
        } else {
            NotifSetting notifSetting = notifSettings.get(0);
            boolean enableNotif = notifSetting.isEnableNotif();
            if (enableNotif) {
                Frag_Notification.startRescheduleAlarmsService(this);
            } else {
                Frag_Notification.startDescheduleAlarmsService(this);
            }
            boolean enableRefillNotif = notifSetting.isEnableRefillNotif();
            if (enableRefillNotif) {
                Frag_Notification.setOneTimeRefillReminder(this);
                Frag_Notification.setRefillReminder(this);
            } else {
                Frag_Notification.cancelRefillReminder(this);
            }
        }
    }
}