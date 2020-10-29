package com.example.drmednotifier;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.drmednotifier.data.User;
import com.example.drmednotifier.data.UserDao;
import com.example.drmednotifier.data.UserDatabase;

import java.util.List;
import java.util.Random;

public class Second_page_get_personaldata extends AppCompatActivity {


    private UserDatabase userDatabase;
    private UserDao userDao;
    private List<User> usersLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page_get_personaldata);


    }




    public void launchActivists(View x){

        Intent i = new Intent(this,Nav_page.class);


        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user_database").allowMainThreadQueries().build();
        userDao = userDatabase.userDao();
        usersLiveData = userDao.getUser();

        String fn = ((EditText)findViewById(R.id.fn)).getText().toString();
        String ln = ((EditText)findViewById(R.id.Name)).getText().toString();
        EditText age = (EditText)findViewById(R.id.age);
        int agee = Integer.parseInt(age.getText().toString());
        User user;
        int userId;
        if (usersLiveData.isEmpty()) {
            userId = new Random().nextInt(Integer.MAX_VALUE);
            user = new User(userId, fn, ln, agee, 2, System.currentTimeMillis());
            userDao.insert(user);
        }
        else{
            userId = usersLiveData.get(0).getUserId();
            user = new User(userId, fn, ln, agee, 2,System.currentTimeMillis());
            userDao.update(user);


        }
        startActivity(i);

    }


    public void launchActivistswithnovalue(View x){

        Intent i = new Intent(this,Nav_page.class);

        startActivity(i);

    }





}