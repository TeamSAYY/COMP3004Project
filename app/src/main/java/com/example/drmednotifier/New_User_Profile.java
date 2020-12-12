package com.example.drmednotifier;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.drmednotifier.data.GenerateRandomInt;
import com.example.drmednotifier.data.User;
import com.example.drmednotifier.data.UserDao;
import com.example.drmednotifier.data.UserDatabase;

import java.util.List;

public class New_User_Profile extends AppCompatActivity {

    private UserDao userDao;
    private List<User> users;

    private EditText editTextFirstName, editTextLastName, editTextAge;
    //  create a textWatcher member
    private final TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues();
        }
    };

    private void checkFieldsForEmptyValues(){
        Button btnSave = (Button) findViewById(R.id.btnSaveChanges);

        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String age = editTextAge.getText().toString();

        if(firstName.equals("") || lastName.equals("") || age.equals("")){
            btnSave.setEnabled(false);
            //btnSave.setTextColor(getResources().getColor(R.color.button_disabled_text_colour));
            btnSave.getBackground().setAlpha(64);
        } else {
            btnSave.setEnabled(true);
            //btnSave.setTextColor(getResources().getColor(R.color.background_default_Color));
            btnSave.getBackground().setAlpha(255);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__user__profile);

        Toolbar toolbar = findViewById(R.id.toolbar_User_Profile);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        editTextFirstName = (EditText) findViewById(R.id.textFirstName);
        editTextLastName = (EditText) findViewById(R.id.textLastName);
        editTextAge = (EditText) findViewById(R.id.textAge);

        editTextFirstName.addTextChangedListener(mTextWatcher);
        editTextLastName.addTextChangedListener(mTextWatcher);
        editTextAge.addTextChangedListener(mTextWatcher);

        checkFieldsForEmptyValues();
        
        // May need a better implementation of accessing database - YS
        UserDatabase userDatabase = UserDatabase.getDatabase(getApplicationContext());
        userDao = userDatabase.userDao();
        
        setUserProperties();

        Button btnSave = findViewById(R.id.btnSaveChanges);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();

                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);

                finish();
            }
        });
    }

    private void saveUser() {
        int userId;

        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();

        int age, gender;

        try {
            age = Integer.parseInt(editTextAge.getText().toString());
        } catch (NumberFormatException e) {
            age = 0;
        }

        if (((RadioButton)findViewById(R.id.btnMale)).isChecked()) gender = 0;
        else if (((RadioButton)findViewById(R.id.btnFemale)).isChecked()) gender = 1;
        else if (((RadioButton)findViewById(R.id.btnOther)).isChecked()) gender = 2;
        else gender = -1;

        User user;

        users = userDao.getUser();

        if (users.isEmpty()) {
            userId = GenerateRandomInt.get();
            user = new User(userId, firstName, lastName, age, gender, System.currentTimeMillis());
            userDao.insert(user);
        } else {
            userId = users.get(0).getUserId();
            String avatar = users.get(0).getAvatar();
            user = new User(userId, firstName, lastName, age, gender, System.currentTimeMillis());
            user.setAvatar(avatar);
            userDao.update(user);
        }
    }

    private void setUserProperties () {
        users = userDao.getUser();

        if (!users.isEmpty()) {
            User user = users.get(0);

            if (user.getFirstName().length() != 0) {
                editTextFirstName.setText(user.getFirstName());
                editTextLastName.setText(user.getLastName());
                editTextAge.setText(String.format("%d", user.getAge()));

                if (user.isMale()) ((RadioButton)findViewById(R.id.btnMale)).setChecked(true);
                else if (user.isFemale()) ((RadioButton)findViewById(R.id.btnFemale)).setChecked(true);
                else if (user.isOthers()) ((RadioButton)findViewById(R.id.btnOther)).setChecked(true);
            }
        }
    }
}