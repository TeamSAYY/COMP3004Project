package com.example.drmednotifier;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drmednotifier.data.GenerateRandomInt;
import com.example.drmednotifier.data.User;
import com.example.drmednotifier.data.UserDao;
import com.example.drmednotifier.data.UserDatabase;

import java.util.List;

/**
 * Activity that requests the user's profile information
 */
public class Second_page_get_personaldata extends AppCompatActivity {
    private EditText editTextFirstName, editTextLastName, editTextAge;

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

    private void checkFieldsForEmptyValues() {
        Button btnConfirm = (Button) findViewById(R.id.btnConfirm);

        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String age = editTextAge.getText().toString();

        if(firstName.equals("") || lastName.equals("") || age.equals("")){
            btnConfirm.setEnabled(false);
            //btnSave.setTextColor(getResources().getColor(R.color.button_disabled_text_colour));
            btnConfirm.getBackground().setAlpha(64);
        } else {
            btnConfirm.setEnabled(true);
            //btnSave.setTextColor(getResources().getColor(R.color.background_default_Color));
            btnConfirm.getBackground().setAlpha(255);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page_get_personaldata);

        editTextFirstName = (EditText) findViewById(R.id.txtFName);
        editTextLastName = (EditText) findViewById(R.id.txtLName);
        editTextAge = (EditText) findViewById(R.id.txtAge);

        editTextFirstName.addTextChangedListener(mTextWatcher);
        editTextLastName.addTextChangedListener(mTextWatcher);
        editTextAge.addTextChangedListener(mTextWatcher);

        checkFieldsForEmptyValues();
    }

    private void saveUser() {
        int userId;

        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();

        int age;
        int gender = -1; // Use -1 as a place holder for empty int field. Can change it later if we come up a better solution. - YS

        try {
            age = Integer.parseInt(editTextAge.getText().toString());
        } catch (NumberFormatException e) {
            age = 0;
        }

        User user;

        UserDatabase userDatabase = UserDatabase.getDatabase(getApplicationContext());
        UserDao userDao = userDatabase.userDao();
        List<User> usersLiveData = userDao.getUser();

        if (usersLiveData.isEmpty()) {
            userId = GenerateRandomInt.get();
            user = new User(userId, firstName, lastName, age, gender, System.currentTimeMillis());
            userDao.insert(user);
        } else { //Will need to remove this later as user can only access this page if they have no user information saved
            if (usersLiveData.get(0).getFirstName().length() == 0) { // If the avatar is set but no other information saved, still show the second page
                userId = usersLiveData.get(0).getUserId();
                String avatar = usersLiveData.get(0).getAvatar();
                user = new User(userId, firstName, lastName, age, gender, System.currentTimeMillis());
                user.setAvatar(avatar);
                userDao.update(user);
            }
        }
    }

    /**
     * Saves user input and launches the next activity
     * @param v The Save button
     */
    public void launchActivity(View v){
        saveUser();

        Intent i = new Intent(this,Nav_page.class);
        finish();
        startActivity(i);
    }

    /**
     * Launches the next activity
     * @param v The Skip button
     */
    public void launchActivitywithnovalue(View v){
        Intent i = new Intent(this,Nav_page.class);
        finish();
        startActivity(i);
    }
}