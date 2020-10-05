

package com.example.drmednotifier;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Animation topAnim, bottomAnim;
    ImageView text;
    TextView logo;
    Button TB1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*set title on top bar*/
       /* setTitle("First Page");*/



        /* test date transfer
        Intent i = getIntent();
       message contain the key "COOL"'s value hello
        String message = i.getStringExtra("COOL");
        ((TextView)findViewById(R.id.test)).setText(message);*/

         /*load animation*/
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anime);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anime);




        text = findViewById(R.id.logotext);
        logo = findViewById(R.id.welcome);
        TB1= findViewById(R.id.Tbutton1);

        /*insert animation*/
        text.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        TB1.setAnimation(bottomAnim);


    }



    public void launchActivity(View x){

        Intent i = new Intent(this,Second_page_get_personaldata.class);
    /*
       String input = ((TextView)findViewById(R.id.source)).getText().toString();
       i.putExtra("COOL",input);*/

        startActivity(i);

    }

}