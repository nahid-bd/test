package com.esit.floristry.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.esit.floristry.fragments.CanceledOrderFragment;
import com.esit.floristry.fragments.ClaimedOrderFragment;
import com.esit.floristry.fragments.ConfirmedOrderFragment;
import com.esit.floristry.fragments.ReceivedOrderFragment;
import com.esit.floristry.fragments.RequestedOrderFragment;
import com.esit.floristry.fragments.ShiftedOrderFragment;
import com.esit.floristry.fragments.SubmittedOrderFragment;
import com.esit.floristry.fragments.UpdatedOrderFragment;
import com.esit.floristry.utility.AppConstants;

public class OrderPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    String orderType;

    public OrderPagerAdapter(FragmentManager fm, int NumOfTabs, String orderType) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.orderType = orderType;
    }


    @Override
    public Fragment getItem(int position) {


        if (orderType.equalsIgnoreCase(AppConstants.PAGER_RECEIVED_ORDER_SELLER)) {
            switch (position) {

                case 0:
                    RequestedOrderFragment tab1 = new RequestedOrderFragment();
                    return tab1;
                case 1:
                    SubmittedOrderFragment tab2 = new SubmittedOrderFragment();
                    return tab2;
                case 2:

                    ConfirmedOrderFragment tab3 = new ConfirmedOrderFragment();
                    return tab3;

                default:
                    return null;
            }
        } else if (orderType.equalsIgnoreCase(AppConstants.PAGER_RELEASED_ORDER)) {
            switch (position) {

                case 0:
                    ShiftedOrderFragment tab1 = new ShiftedOrderFragment();
                    return tab1;
                case 1:
                    ReceivedOrderFragment tab2 = new ReceivedOrderFragment();
                    return tab2;
                case 2:

                    ClaimedOrderFragment tab3 = new ClaimedOrderFragment();
                    return tab3;

                default:
                    return null;
            }

        } else if (orderType.equalsIgnoreCase(AppConstants.PAGER_SENT_ORDER)) {
            switch (position) {

                case 0:
                    RequestedOrderFragment tab1 = new RequestedOrderFragment();
                    return tab1;
                case 1:
                    CanceledOrderFragment tab2 = new CanceledOrderFragment();
                    return tab2;

                default:
                    return null;
            }

        } else if (orderType.equalsIgnoreCase(AppConstants.PAGER_RESPONDED_ORDER)) {
            switch (position) {

                case 0:
                    UpdatedOrderFragment tab1 = new UpdatedOrderFragment();
                    return tab1;
                case 1:
                    ConfirmedOrderFragment tab2 = new ConfirmedOrderFragment();
                    return tab2;


                default:
                    return null;
            }

        } else if (orderType.equalsIgnoreCase(AppConstants.PAGER_RECEIVED_ORDER_RETAILER)) {
            switch (position) {

                case 0:
                    ShiftedOrderFragment tab1 = new ShiftedOrderFragment();
                    return tab1;
                case 1:
                    ReceivedOrderFragment tab2 = new ReceivedOrderFragment();
                    return tab2;
                case 2:

                    ClaimedOrderFragment tab3 = new ClaimedOrderFragment();
                    return tab3;

                default:
                    return null;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
