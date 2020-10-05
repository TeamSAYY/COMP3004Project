package com.example.drmednotifier;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Nav_page extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    String N,A;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_page);

       bottomNavigationView = findViewById(R.id.bottomNavigationView);
       frameLayout = findViewById(R.id.frameLayoutView);







        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);


/*
        Intent i = getIntent();
        N = i.getStringExtra("Name");
        A = i.getStringExtra("Age");
*/


    }




/*
    Frag_Add add = new Frag_Add();
    Frag_Home home = new Frag_Home();
    Frag_Notification notification = new Frag_Notification();
    Frag_Selfreport self_report = new Frag_Selfreport();
    Frag_Setting setting = new Frag_Setting();*/





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        /*switch(item.getItemId()){

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,home).commit();
                return true;

            case R.id.add_product:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,add).commit();
                return true;
            case R.id.self_report:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,self_report).commit();
                return true;
            case R.id.notification:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,notification).commit();
                return true;
            case R.id.Setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,setting).commit();
                return true;

        }


        return false;*/
    }



   /* //CHECKBOX CLICK DETECT
    public void CheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_top:
                if (checked){
                    CheckBox x = view.findViewById(R.id.checkbox_top);
                    x.setText("Checked Thank u!");
                    ;
                    // Put some meat on the sandwich
                }

                else
                    // Remove the meat
                    break;


        }
    }*/










   /* public  String getNamed(){
        return N;
    }
    public  String getAge(){
        return A;
    }*/


}