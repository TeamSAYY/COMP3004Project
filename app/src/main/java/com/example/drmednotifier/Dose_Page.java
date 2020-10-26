package com.example.drmednotifier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class Dose_Page extends AppCompatActivity {

    private TextView theDate;
    private Toolbar theToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dose__page);

        theDate = (TextView) findViewById(R.id.dose_date);
        theToolbar =  (Toolbar) findViewById(R.id.toolbar_Dose);

        Intent incomingIntent = getIntent();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        int year = incomingIntent.getIntExtra("YEAR", calendar.get(Calendar.YEAR));
        int month = incomingIntent.getIntExtra("MONTH", calendar.get(Calendar.MONTH) + 1);
        int day = incomingIntent.getIntExtra("DAY", calendar.get(Calendar.DAY_OF_MONTH));

        String date = month + "/" + day + "/" + year;
        theDate.setText(date);

        theToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); }
        });
    }
}