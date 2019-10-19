package com.esit.floristry.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.esit.floristry.R;
import com.esit.floristry.adapter.TransactionViewPagerAdapter;
import com.esit.floristry.storageUtility.AppSharedPreference;

public class TransactionsActivity extends AppCompatActivity {
    public Toolbar mToolbar;
    TextView tvHomeToolbarTitle;
    LinearLayout llBack;

    String businessType = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        mToolbar = (Toolbar) findViewById(R.id.tbToolbarback);
        tvHomeToolbarTitle = (TextView) mToolbar.findViewById(R.id.tvHomeToolbarTitle);
        tvHomeToolbarTitle.setText("TRANSACTIONS");
        llBack = mToolbar.findViewById(R.id.llBack);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if (AppSharedPreference.getUserType(this).equalsIgnoreCase("seller")) {
            businessType = "RETAILER";
        } else {
            businessType = "SELLER";
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("RECENT"));
        tabLayout.addTab(tabLayout.newTab().setText(businessType));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#ff0000"));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ff0000"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.transactionViewPager);
        final TransactionViewPagerAdapter adapter = new TransactionViewPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.offsetLeftAndRight(1);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
