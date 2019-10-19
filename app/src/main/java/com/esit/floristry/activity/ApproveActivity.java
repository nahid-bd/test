package com.esit.floristry.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.esit.floristry.R;
import com.esit.floristry.fragments.ApproveFragment;
import com.esit.floristry.storageUtility.AppSharedPreference;

public class ApproveActivity extends AppCompatActivity {
    public Toolbar mToolbar;
    TextView tvHomeToolbarTitle;
    LinearLayout llBack;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve);

        mToolbar = (Toolbar) findViewById(R.id.tbToolbarback);
        tvHomeToolbarTitle = (TextView) mToolbar.findViewById(R.id.tvHomeToolbarTitle);

        llBack = mToolbar.findViewById(R.id.llBack);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if (AppSharedPreference.getUserType(this).equalsIgnoreCase("seller")) {
            tvHomeToolbarTitle.setText("APPROVE PAYMENT");
        } else {
            tvHomeToolbarTitle.setText("PENDING APPROVAL");
        }


        Fragment fragment = new ApproveFragment();

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.approveListContainer, fragment);
            fragmentTransaction.commit();

        } else {
            Toast.makeText(this, "fragment null", Toast.LENGTH_LONG).show();
        }


    }
}


