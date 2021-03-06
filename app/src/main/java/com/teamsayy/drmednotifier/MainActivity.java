package com.teamsayy.drmednotifier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamsayy.drmednotifier.data.NotifSetting;
import com.teamsayy.drmednotifier.data.NotifSettingDao;
import com.teamsayy.drmednotifier.data.NotifSettingDatabase;
import com.teamsayy.drmednotifier.data.User;
import com.teamsayy.drmednotifier.data.UserDao;
import com.teamsayy.drmednotifier.data.UserDatabase;

import java.util.List;

/**
 * Activity that start as a main entry point
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load animation
        Animation topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anime);
        Animation bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anime);

        ImageView text = findViewById(R.id.logotext);
        TextView logo = findViewById(R.id.welcome);
        Button TB1 = findViewById(R.id.Tbutton1);

        //insert animation
        text.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        TB1.setAnimation(bottomAnim);

        initNotifSettings();
    }

    /**
     * Launches the next activity
     * @param v The button that calls this method
     */
    public void launchActivity(View v){
        if (existingUserInfo()) {
            Intent i = new Intent(this, Nav_page.class);
            startActivity(i);
            finish();
        } else {
            Intent i = new Intent(this, Second_page_get_personaldata.class);
            startActivity(i);
            finish();
        }
    }

    private boolean existingUserInfo() {
        UserDatabase userDatabase = UserDatabase.getDatabase(getApplicationContext());
        UserDao userDao = userDatabase.userDao();
        List<User> users = userDao.getUser();

        // If the avatar is set but no other information saved, still show the second page
        return (!users.isEmpty() && users.get(0).getFirstName().length() != 0);

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