package com.example.drmednotifier;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

public class Popup_Window extends Setting_Page{

    ImageView image;
    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



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

        setResult(RESULT_OK, intent);
        finish();


    }


    public void avatar_change1(View view) {



        Intent intent = new Intent();
        intent.putExtra("avatar_change_1", "a2");
        intent.putExtra("avatar_change_12", "a1");
        setResult(RESULT_OK, intent);
        finish();
    }


}
