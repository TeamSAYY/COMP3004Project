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
    String Nametransfer,Agetransfer,avatar_transfer,avatar_count;




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

            Nametransfer  = getIntent().getStringExtra("Name");
            intent.putExtra("Name_transfer",Nametransfer);

            Agetransfer  = getIntent().getStringExtra("Age");
            intent.putExtra("Age_transfer",Agetransfer);


            intent.putExtra("avatar_transfer",avatar_count);



            startActivityForResult(intent,1);
            /*startActivity(intent);*/
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                avatar_count = data.getStringExtra("current_avatar");


            }
        }
    }




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;


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

    public void click_User_Profile(View view) {
        Intent i = new Intent(this, New_User_Profile.class);
        startActivity(i);
    }







/*

    public  String getNamed(){
        Intent intent = getIntent();
        String NN  = intent.getStringExtra("Name");

        return NN;
    }
    public  String getAge(){
        return A;
    }

*/
}