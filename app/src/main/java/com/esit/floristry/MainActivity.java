package com.esit.floristry;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.esit.floristry.activity.SplashActivity;
import com.esit.floristry.retailerFragments.HomeFragmentRetailer;
import com.esit.floristry.sellerFragments.HomeFragmentSeller;
import com.esit.floristry.storageUtility.AppSharedPreference;

public class MainActivity extends AppCompatActivity {

    String userName;
    String userType;
    Context context;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        userName= AppSharedPreference.getUserName(context);
        userType=AppSharedPreference.getUserType(context);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.ab_layout);

        Fragment fragment = null;
        if (userType.equalsIgnoreCase("retailer")) {
            fragment = new HomeFragmentRetailer();
        } else if (userType.equalsIgnoreCase("seller")) {
            fragment = new HomeFragmentSeller();
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainActivityContainer, fragment);
            fragmentTransaction.commit();

        }else {
            Toast.makeText(context,"fragment null",Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.logOut:
                // clear all data

                AppSharedPreference.setIslogIn(context,false);

                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.settings:
             //   Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
              //  startActivity(profileIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Click twice to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
