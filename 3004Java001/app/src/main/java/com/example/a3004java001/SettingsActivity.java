package com.example.a3004java001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {


    Animation topAnim, bottomAnim;
    ImageView text;
    TextView logo;
    Button TB1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("First Page");



        /* test date transfer
        Intent i = getIntent();
       message contain the key "COOL"'s value hello
        String message = i.getStringExtra("COOL");
        ((TextView)findViewById(R.id.test)).setText(message);*/


        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anime);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anime);




        text = findViewById(R.id.logotext);
        logo = findViewById(R.id.welcome);
        TB1= findViewById(R.id.Tbutton1);

        text.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        TB1.setAnimation(bottomAnim);


    }



    public void launchActivity(View x){

        Intent i = new Intent(this,Second_page_skip_input.class);
     /* test data transfer
       String input = ((TextView)findViewById(R.id.source)).getText().toString();
       i.putExtra("COOL",input);*/

        startActivity(i);

    }

}