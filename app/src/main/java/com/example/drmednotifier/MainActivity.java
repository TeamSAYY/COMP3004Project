

package com.example.drmednotifier;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.drmednotifier.data.User;
import com.example.drmednotifier.data.UserDao;
import com.example.drmednotifier.data.UserDatabase;

import java.util.List;

//import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    Animation topAnim, bottomAnim;
    ImageView text;
    TextView logo;
    Button TB1;

    private UserDatabase userDatabase;
    private UserDao userDao;
    private List<User> usersLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*set title on top bar*/
       /* setTitle("First Page");*/

        /* test date transfer
        Intent i = getIntent();
       message contain the key "COOL"'s value hello
        String message = i.getStringExtra("COOL");
        ((TextView)findViewById(R.id.test)).setText(message);*/

         /*load animation*/
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anime);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anime);

        text = findViewById(R.id.logotext);
        logo = findViewById(R.id.welcome);
        TB1= findViewById(R.id.Tbutton1);

        /*insert animation*/
        text.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        TB1.setAnimation(bottomAnim);
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
    /*
       String input = ((TextView)findViewById(R.id.source)).getText().toString();
       i.putExtra("COOL",input);*/

//        Intent i = new Intent(this,Second_page_get_personaldata.class);
//        startActivity(i);

    }

    private boolean existingUserInfo() {
        userDatabase = Room.databaseBuilder(this, UserDatabase.class, "user_database").allowMainThreadQueries().build();
        userDao = userDatabase.userDao();
        usersLiveData = userDao.getUser();

        if (!usersLiveData.isEmpty()) {
            if (usersLiveData.get(0).getFirstName().length() != 0) {
                Log.d("myTag", "Existing user info");
                return true;
            } else { // If the avatar is set but no other information saved, still show the second page
                Log.d("myTag", "Existing user info BUT it only contains avatar info");
                return false;
            }
        } else {
            Log.d("myTag", "NOT Existing user info");
            return false;
        }
    }
}