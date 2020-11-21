package com.example.drmednotifier;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Nav_page extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_page);

        Toolbar toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
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

            startActivityForResult(intent,1);
            /*startActivity(intent);*/
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutView);
                FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
                fragTransaction.detach(currentFragment);
                fragTransaction.attach(currentFragment);
                fragTransaction.commit();
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutView);
                if (currentFragment instanceof Frag_Home) {
                    FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
                    fragTransaction.detach(currentFragment);
                    fragTransaction.attach(currentFragment);
                    fragTransaction.commit();
                }
            }
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
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
        startActivityForResult(i, 2);
    }
}