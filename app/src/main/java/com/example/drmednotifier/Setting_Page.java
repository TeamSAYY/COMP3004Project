package com.example.drmednotifier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.drmednotifier.data.User;
import com.example.drmednotifier.data.UserDao;
import com.example.drmednotifier.data.UserDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Setting_Page extends AppCompatActivity {
    private final int[] image = new int[]{R.drawable.ic_baseline_list_24,R.drawable.ic_dose};
    private final String[] headline = new String[]{"Medication List","Today's Medication Dosage"};
    private final String[] bottomline = new String[]{"View all the Med. you are taking","View the Med. needed to be taken today"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__page);

        setUserProperties();

        Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> {

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
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
        int[] to ={R.id.home_avatar,R.id.headline,R.id.bottomline};
        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(),list,R.layout.listview_case,from,to);
        listView.setAdapter(simpleAdapter);

        /*list view click*/
        listView.setOnItemClickListener((adapterView, v, position, l) -> {
            if(position==0){
                Intent intent = new Intent(Setting_Page.this, Medicine_List.class);
                startActivity(intent);
            }

            if(position==1){
                Intent intent = new Intent(Setting_Page.this, Dose_Page.class);
                startActivity(intent);
            }
        });
    }

    /*transfer to New_User-Profile when click layout bar*/
    public void click_name_tag(View view) {
        Intent i = new Intent(this,New_User_Profile.class);
        startActivityForResult(i, 2);
    }

    public void avatar_click(View view) {
        Intent i = new Intent(this, Popup_Window.class);
        startActivityForResult(i, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 || requestCode == 2) {
            if (resultCode == RESULT_OK) {
                setUserProperties();
            }
        }
    }

    private void setUserProperties () {
        UserDatabase userDatabase = UserDatabase.getDatabase(getApplicationContext());
        UserDao userDao = userDatabase.userDao();
        List<User> usersLiveData = userDao.getUser();

        ImageView z = findViewById(R.id.home_avatar);

        String g = "";

        if (!usersLiveData.isEmpty()) {
            User user = usersLiveData.get(0);

            if (user.getFirstName().length() != 0) {
                String name = user.getFirstName() + " " + user.getLastName();
                int gender = user.getGender();
                if (gender == 0) {
                    g = "Male";
                } else if (gender == 1) {
                    g = "Female";
                } else if (gender == 2) {
                    g = "Others";
                }

                String age_gender;
                if (gender == -1) age_gender = user.getAge() + " years old";
                else age_gender = user.getAge() + " years old, " + g;

                ((TextView) findViewById(R.id.headline_name)).setText(name);
                ((TextView) findViewById(R.id.bottomline_age)).setText(age_gender);
            }

            String default_avatar = user.getAvatar();
            if (default_avatar.equals("a1")) {
                z.setImageResource(R.drawable.a1);
            }
            if (default_avatar.equals("a2")) {
                z.setImageResource(R.drawable.a2);
            }
        }
    }
}