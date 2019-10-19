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
import com.esit.floristry.adapter.OrderPagerAdapter;
import com.esit.floristry.utility.AppConstants;

public class SentOrderActivity extends AppCompatActivity {

    public Toolbar mToolbar;
    TextView tvHomeToolbarTitle;
    LinearLayout llBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_order);

        mToolbar = (Toolbar) findViewById(R.id.tbToolbarback);
        tvHomeToolbarTitle = (TextView) mToolbar.findViewById(R.id.tvHomeToolbarTitle);
        tvHomeToolbarTitle.setText("SENT ORDER");
        llBack = mToolbar.findViewById(R.id.llBack);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_sent_order);
        tabLayout.addTab(tabLayout.newTab().setText("REQUESTED"));
        tabLayout.addTab(tabLayout.newTab().setText(" CANCELED"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorWhite), getResources().getColor(R.color.yellow_1));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.yellow_1));
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager_sent_order);
        final OrderPagerAdapter adapter = new OrderPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(), AppConstants.PAGER_SENT_ORDER);
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


