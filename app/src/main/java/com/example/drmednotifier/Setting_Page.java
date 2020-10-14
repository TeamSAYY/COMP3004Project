package com.example.drmednotifier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Setting_Page extends AppCompatActivity {


int[] image = new int[]{R.drawable.nav_selfreport,R.drawable.nav_home};
String[] headline = new String[]{"Medicine List","Modify Current Medicine"};
String[] bottomline = new String[]{"Display Your Medicine List","Change Your Current Medicine Plan"};
SwitchCompat switchCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__page);


        TextView a = findViewById(R.id.headline_name);
        a.setText("Somebody");

        TextView b = findViewById(R.id.bottomline_gender);
        b.setText("male, 19");


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



        /*
        ArrayAdapter<CharSequence> spin = ArrayAdapter.createFromResource(this,R.array.spin_content,android.R.layout.simple_spinner_item);
        spin.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spin);
        spinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);
*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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




        ListView listView= findViewById(R.id.listview);
        List<HashMap<String,String>> list =new ArrayList<>();

        for(int i = 0; i<2;i++){

            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("image",Integer.toString(image[i]));
            hashMap.put("headline",headline[i]);
            hashMap.put("bottomline",bottomline[i]);

            list.add(hashMap);

        }


            String[] from = {"image","headline","bottomline"};
            int[] to ={R.id.image_view,R.id.headline,R.id.bottomline};
            SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(),list,R.layout.listview_case,from,to);
            listView.setAdapter(simpleAdapter);


    }


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


}