package com.example.drmednotifier;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.room.Room;

import com.example.drmednotifier.data.User;
import com.example.drmednotifier.data.UserDao;
import com.example.drmednotifier.data.UserDatabase;

import java.util.List;
import java.util.Random;

public class Popup_Window extends Setting_Page{



    private UserDatabase userDatabase;
    private UserDao userDao;
    private List<User> usersLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user_database").allowMainThreadQueries().build();
        userDao = userDatabase.userDao();
        usersLiveData = userDao.getUser();



        setContentView(R.layout.popup_window_layout);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int) (height*.6));













    }


    public void avatar_change(View view) {


        Intent intent = new Intent();
        intent.putExtra("avatar_change_1", "a1");
        intent.putExtra("avatar_change_11", "a1");

       /* if (!usersLiveData.isEmpty()) {
            User user = usersLiveData.get(0);
            if(avatar_T != null ){ user.setAvatar("a1");}

        }*/


        setResult(RESULT_OK, intent);
        finish();


    }


    public void avatar_change1(View view) {



        Intent intent = new Intent();
        intent.putExtra("avatar_change_1", "a2");
        intent.putExtra("avatar_change_12", "a2");

        /*
        if (!usersLiveData.isEmpty()) {
            User user = usersLiveData.get(0);
            if(avatar_T != null ){ user.setAvatar("a2");}


        }*/

        setResult(RESULT_OK, intent);
        finish();
    }


}
