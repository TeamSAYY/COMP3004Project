package com.teamsayy.drmednotifier;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Activity that navigates between primary destinations in the app
 */
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

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutView, new Frag_Home(), "HOME_FRAGMENT").commit();
    }

    /**
     * Initialize the contents of the Activity's settings menu
     * @param menu Settings menu
     * @return Return true for the menu to be displayed
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tool_bar_menu,menu);
        return true;
    }

    /**
     * Launches the Settings activity
     * @param item The menu item that was selected
     * @return Return false to allow normal menu processing to proceed, true to consume it here
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.toolbarsetting) {
            Intent intent = new Intent(this, Setting_Page.class);
            startActivityForResult(intent,1);
            return true;
        }
        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Comes from the Settings activity
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutView);
                FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
                fragTransaction.detach(currentFragment);
                fragTransaction.attach(currentFragment);
                fragTransaction.commit();
            }
        }
        // Comes from the User Profile activity
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

        /**
         * Called when the bottom navigation bar item is clicked
         * @param item Bottom navigation menu item
         * @return true to display the item as the selected item
         */
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment homeFragment = getSupportFragmentManager().findFragmentByTag("HOME_FRAGMENT");
            Fragment addFragment = getSupportFragmentManager().findFragmentByTag("ADD_FRAGMENT");
            Fragment reportFragment = getSupportFragmentManager().findFragmentByTag("REPORT_FRAGMENT");
            Fragment notifFragment = getSupportFragmentManager().findFragmentByTag("NOTIF_FRAGMENT");

            Fragment selectedFragment = null;
            String tag = "";
            boolean isSameFragment = false;
            switch (item.getItemId()) {
                case R.id.home:
                    if (homeFragment != null && homeFragment.isVisible()) {
                        isSameFragment = true;
                    } else {
                        selectedFragment = new Frag_Home();
                        tag = "HOME_FRAGMENT";
                    }
                    break;
                case R.id.add_product:
                    if (addFragment != null && addFragment.isVisible()) {
                        isSameFragment = true;
                    } else {
                        selectedFragment = new Frag_Add();
                        tag = "ADD_FRAGMENT";
                    }
                    break;
                case R.id.self_report:
                    if (reportFragment != null && reportFragment.isVisible()) {
                        isSameFragment = true;
                    } else {
                        selectedFragment = new Frag_Selfreport();
                        tag = "REPORT_FRAGMENT";
                    }
                    break;
                case R.id.notification:
                    if (notifFragment != null && notifFragment.isVisible()) {
                        isSameFragment = true;
                    } else {
                        selectedFragment = new Frag_Notification();
                        tag = "NOTIF_FRAGMENT";
                    }
                    break;
            }
            item.setChecked(true);

            if (!isSameFragment) {
                assert selectedFragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutView, selectedFragment, tag).commit();
            }

            return true;
        }
    };

    /**
     * Launches the User Profile activity
     * @param view The linear layout, that displays user information, located on the top of the Home fragment
     */
    public void click_User_Profile(View view) {
        Intent i = new Intent(this, New_User_Profile.class);
        startActivityForResult(i, 2);
    }

    /**
     * Called when the activity has detected the user's press of the back key
     */
    @Override
    public void onBackPressed() {
        Fragment addFragment = getSupportFragmentManager().findFragmentByTag("ADD_FRAGMENT");
        // If the Add fragment is currently active, the user's press of the back key triggers an alert dialog
        if (addFragment != null && addFragment.isVisible()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this); //alert for confirm to delete
            builder.setMessage("Are you sure to leave?");    //set message
            //not removing items if cancel is done
//when click on DELETE
            builder.setPositiveButton("LEAVE", (dialog, which) -> {
                // Nav_page.super.onBackPressed();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }).setNegativeButton("CANCEL", (dialog, which) -> {

            }).show();  // show alert dialog
        } else {
            // super.onBackPressed();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    }
}