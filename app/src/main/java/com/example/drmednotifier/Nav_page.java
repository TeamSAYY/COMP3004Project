package com.example.drmednotifier;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Nav_page extends AppCompatActivity{

    private Toolbar toolbar;
    BottomNavigationView navView;
    FrameLayout frameLayout;
    String N,A;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_page);

        toolbar=findViewById(R.id.Toolbar);

        setSupportActionBar(toolbar);




        navView= findViewById(R.id.bottomNavigationView);
        navView.setSelectedItemId(R.id.home);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutView, new Frag_Home()).commit();

//        frameLayout = findViewById(R.id.frameLayoutView);
//
//        BottomNavigationView.setSelectedItemId(R.id.home);
//        BottomNavigationView.setOnNavigationItemSelectedListener(this);


/*
        Intent i = getIntent();
        N = i.getStringExtra("Name");
        A = i.getStringExtra("Age");
*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tool_bar_menu,menu);



        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.toolbarsetting) {
            Intent intent = new Intent(this, Setting_Page.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

/*
    Frag_Add add = new Frag_Add();
    Frag_Home home = new Frag_Home();
    Frag_Notification notification = new Frag_Notification();
    Frag_Selfreport self_report = new Frag_Selfreport();
    Frag_Setting setting = new Frag_Setting();*/




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            Activity selectedActivity = null;

            switch (item.getItemId()) {
                case R.id.home:
                    selectedFragment = new Frag_Home();

                    break;
                case R.id.add_product:
                    selectedFragment = new Frag_Add();

                    break;
                case R.id.self_report:
                    selectedFragment = new Frag_Selfreport();

                    break;
                case R.id.notification:
                    selectedFragment = new Frag_Notification();

                    break;

            }
            item.setChecked(true);
            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutView, selectedFragment).commit();

            return false;
        }
    };








   /* public  String getNamed(){
        return N;
    }
    public  String getAge(){
        return A;
    }*/


}