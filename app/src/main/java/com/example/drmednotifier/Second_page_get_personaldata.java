package com.example.drmednotifier;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Second_page_get_personaldata extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page_get_personaldata);


    }




    public void launchActivists(View x){

        Intent i = new Intent(this,Nav_page.class);

        String Namee = ((EditText)findViewById(R.id.Name)).getText().toString();
        i.putExtra("Name",Namee);

        String Agee = ((EditText)findViewById(R.id.age)).getText().toString();
        i.putExtra("Age",Agee);



        startActivity(i);

    }


    public void launchActivistswithnovalue(View x){

        Intent i = new Intent(this,Nav_page.class);

        startActivity(i);

    }





}