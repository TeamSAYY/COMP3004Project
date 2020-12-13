package com.teamsayy.drmednotifier;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Edit_Medication extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medication);

        Toolbar toolbar = findViewById(R.id.toolbar_Edit_Medication);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_Edit_Medication, new Frag_Add()).commit();
    }
}
