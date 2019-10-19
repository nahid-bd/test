package com.esit.floristry.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.esit.floristry.fragments.RecentTransactionFragment;
import com.esit.floristry.fragments.UserWiseTransactionFragment;

public class TransactionViewPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public TransactionViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                    RecentTransactionFragment tab1 = new RecentTransactionFragment();
                  return tab1;
            case 1:
                   UserWiseTransactionFragment tab2 = new UserWiseTransactionFragment();
                   return tab2;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
