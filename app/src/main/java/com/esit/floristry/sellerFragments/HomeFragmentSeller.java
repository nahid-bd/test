package com.esit.floristry.sellerFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.esit.floristry.R;
import com.esit.floristry.activity.ApproveActivity;
import com.esit.floristry.activity.OwnerListAcivity;
import com.esit.floristry.activity.ReceivedOrderActivity;
import com.esit.floristry.activity.ReleasedOrderActivity;
import com.esit.floristry.activity.TransactionsActivity;


public class HomeFragmentSeller extends Fragment {

    LinearLayout llOwnerList;
    LinearLayout llReceivedOrderSeller;
    LinearLayout llReleasedOrder;
    LinearLayout llTransaction;
    LinearLayout llPaymentApprove;

    public HomeFragmentSeller() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_seller, container, false);

        llOwnerList = view.findViewById(R.id.llOwnerList);
        llReceivedOrderSeller = view.findViewById(R.id.llReceivedOrderSeller);
        llOwnerList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OwnerListAcivity.class);
                startActivity(intent);
            }
        });

        llReceivedOrderSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReceivedOrderActivity.class);
                startActivity(intent);
            }
        });

        llReleasedOrder = view.findViewById(R.id.llReleasedOrder);
        llReleasedOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReleasedOrderActivity.class);
                startActivity(intent);
            }
        });

        llTransaction = view.findViewById(R.id.llTransaction);
        llTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TransactionsActivity.class);
                startActivity(intent);
            }
        });

        llPaymentApprove=view.findViewById(R.id.llPaymentApprove);
        llPaymentApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ApproveActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
