package com.example.drmednotifier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drmednotifier.data.User;
import com.example.drmednotifier.data.UserDao;
import com.example.drmednotifier.data.UserDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Setting_Page extends AppCompatActivity {


    int[] image = new int[]{R.drawable.ic_baseline_list_24,R.drawable.ic_dose};
    String[] headline = new String[]{"Medicine List","Today's Dose"};
    String[] bottomline = new String[]{"Display Your Medicine List","View Today's Medicine Dose  "};
    SwitchCompat switchCompat;
    String current_avatar;
    String avatar_T;

    private UserDatabase userDatabase;
    private UserDao userDao;
    private List<User> usersLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__page);

        updateUserInfo();

        Intent Namm = getIntent();
        //String Namee  = Namm.getStringExtra("Name_transfer");
        //String Agee  = Namm.getStringExtra("Age_transfer");
        avatar_T  = Namm.getStringExtra("avatar_transfer");


        /*TextView a = (TextView) findViewById(R.id.headline_name);
        a.setText(Namee);

        TextView b = (TextView) findViewById(R.id.bottomline_age);
        b.setText(Agee);*/
       // b.setText(avatar_T);

        ImageView z = findViewById(R.id.image_view);
        /*THIS IS SOME SERIOUS SHIT*/
        /*EQUAL DOESNT WORK*/
        /*COMPARE DOESNT WORK*/
        /*THE DISPLAY IS FINE */
        /*DOUBLE DATA TRANSFER FROM POP TO NAV*/


        if (avatar_T != null && avatar_T.equals("a1")) {z.setImageResource(R.drawable.a1);}
        if (avatar_T != null && avatar_T.equals("a2")) {z.setImageResource(R.drawable.a2);}

        /*spinner set up*/
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        List<String> s_list = new ArrayList<>();
        s_list.add("Deafult Music");
        s_list.add("Light Music");
        s_list.add("Pop Music");
        s_list.add("Rock Hard");


        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,s_list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#D81B60"));
                ((TextView) parent.getChildAt(0)).setTextSize(15);
                String text = parent.getItemAtPosition(position).toString();
                if(text=="Deafult Music"){

                }
                else{
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        Toolbar toolbar =  findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("current_avatar", current_avatar);
                setResult(RESULT_OK, intent);
                finish();



                /*
                Intent intent = new Intent(Setting_Page.this, Nav_page.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/
            }
        });








        /*switch */
    switchCompat =(SwitchCompat)  findViewById(R.id.Switch);
    switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if(isChecked){
                Toast.makeText(getApplicationContext(), "Switch is ON", Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(getApplicationContext(), "Switch is OFF", Toast.LENGTH_SHORT).show();
            }



        }
    });



        /*Hash map for list view*/
        ListView listView= findViewById(R.id.listview);
        List<HashMap<String,String>> list =new ArrayList<>();
        for(int i = 0; i<2;i++){

            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("image",Integer.toString(image[i]));
            hashMap.put("headline",headline[i]);
            hashMap.put("bottomline",bottomline[i]);

            list.add(hashMap);

        }

            /*bar menu set up*/
            String[] from = {"image","headline","bottomline"};
            int[] to ={R.id.image_view,R.id.headline,R.id.bottomline};
            SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(),list,R.layout.listview_case,from,to);
            listView.setAdapter(simpleAdapter);







        /*list view click*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long l) {

                if(position==0){


                    Intent intent = new Intent(Setting_Page.this, Medicine_List.class);
                    startActivity(intent);

                }
                if(position==1){


                    Intent intent = new Intent(Setting_Page.this, Dose_Page.class);
                    startActivity(intent);

                }


            }
        });








    }

    /*checkbox*/
    public void CheckboxClickCheck(View view) {
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
                if (!checked ){
                    CheckBox x = view.findViewById(R.id.checkbox_top);
                    x.setText("Not checked!! Loser");
                    ;
                    // Put some meat on the sandwich
                }
                break;
        }




    }

    /*transfer to New_User-Profile when click layout bar*/
    public void click_name_tag(View view) {
        Intent i = new Intent(this,New_User_Profile.class);
        //startActivity(i);
        startActivityForResult(i, 2);
        //finish();
    }

    public void avatar_click(View view) {


        Intent i = new Intent(this, Popup_Window.class);
        startActivityForResult(i, 1);
        ImageView image =findViewById(R.id.image_view);

    }

    private void updateUserInfo() {
        userDatabase = Room.databaseBuilder(this, UserDatabase.class, "user_database").allowMainThreadQueries().build();
        userDao = userDatabase.userDao();
        usersLiveData = userDao.getUser();

        if (!usersLiveData.isEmpty()) {
            String fullName = "";
            String age = "";
            User user = usersLiveData.get(0);
            fullName = user.getFirstName() + " " + user.getLastName();
            age = String.format("%d", user.getAge()) + " years old";

            ((TextView) findViewById(R.id.headline_name)).setText(fullName);
            ((TextView) findViewById(R.id.bottomline_age)).setText(age);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String s = data.getStringExtra("avatar_change_1");
                ImageView image =findViewById(R.id.image_view);

                if(s.equals("a1")){ image.setImageResource(R.drawable.a1);
                    current_avatar="a1";
                }
                if(s.equals("a2")){ image.setImageResource(R.drawable.a2);
                    current_avatar="a2";
                }
            }
        }
        else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                updateUserInfo();
            }
        }
    }


}