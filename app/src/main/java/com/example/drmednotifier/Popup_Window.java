package com.example.drmednotifier;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.drmednotifier.data.GenerateRandomInt;
import com.example.drmednotifier.data.User;
import com.example.drmednotifier.data.UserDao;
import com.example.drmednotifier.data.UserDatabase;

import java.util.List;

public class Popup_Window extends Setting_Page{
    private UserDao userDao;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserDatabase userDatabase = UserDatabase.getDatabase(getApplicationContext());
        userDao = userDatabase.userDao();
        users = userDao.getUser();

        setContentView(R.layout.popup_window_layout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int) (height*.6));
    }

    public void avatar_change(View view) {
        Intent intent = new Intent();

        User user;
        if (users.isEmpty()) {
            int userId = GenerateRandomInt.get();
            user = new User(userId, "", "", -1, -1, System.currentTimeMillis());
            user.setAvatar("a1");
            userDao.insert(user);
        } else {
            user = users.get(0);
            user.setAvatar("a1");
            userDao.update(user);
        }

        setResult(RESULT_OK, intent);
        finish();
    }

    public void avatar_change1(View view) {
        Intent intent = new Intent();

        User user;
        if (users.isEmpty()) {
            int userId = GenerateRandomInt.get();
            user = new User(userId, "", "", -1, -1, System.currentTimeMillis());
            user.setAvatar("a2");
            userDao.insert(user);
        } else {
            user = users.get(0);
            user.setAvatar("a2");
            userDao.update(user);
        }

        setResult(RESULT_OK, intent);
        finish();
    }
}
