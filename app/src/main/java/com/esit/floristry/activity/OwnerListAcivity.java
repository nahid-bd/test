package com.esit.floristry.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.esit.floristry.R;
import com.esit.floristry.retailerFragments.OwnerFragmentRetailer;
import com.esit.floristry.sellerFragments.OwnerFragmentSeller;
import com.esit.floristry.storageUtility.AppSharedPreference;

public class OwnerListAcivity extends AppCompatActivity {

    Context context;
    String userType;

    public Toolbar mToolbar;
    TextView tvHomeToolbarTitle;
    LinearLayout llBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_list);
        context = this;
        userType = AppSharedPreference.getUserType(context);

      //  getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mToolbar = (Toolbar) findViewById(R.id.tbToolbarback);
        tvHomeToolbarTitle = (TextView) mToolbar.findViewById(R.id.tvHomeToolbarTitle);

        llBack = mToolbar.findViewById(R.id.llBack);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Fragment fragment = null;

        if (userType.equalsIgnoreCase("retailer")) {
           // getSupportActionBar().setCustomView(R.layout.owner_actionbar_layout);
            tvHomeToolbarTitle.setText("Seller List");
            fragment = new OwnerFragmentRetailer();
        } else if (userType.equalsIgnoreCase("seller")) {
            tvHomeToolbarTitle.setText("Retailer List");
          //  getSupportActionBar().setCustomView(R.layout.seller_actionbar_layout);
            fragment = new OwnerFragmentSeller();
        }


        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.ownerListContainer, fragment);
            fragmentTransaction.commit();

        } else {
            Toast.makeText(context, "fragment null", Toast.LENGTH_LONG).show();
        }
    }
}
