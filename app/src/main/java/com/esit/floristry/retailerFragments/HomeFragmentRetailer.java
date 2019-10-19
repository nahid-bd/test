package com.esit.floristry.retailerFragments;


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
import com.esit.floristry.activity.ReceivedOrderRetailerActivity;
import com.esit.floristry.activity.RespondedOrderActivity;
import com.esit.floristry.activity.SentOrderActivity;
import com.esit.floristry.activity.TransactionsActivity;


public class HomeFragmentRetailer extends Fragment {


    public HomeFragmentRetailer() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_retailer, container, false);

        LinearLayout llSellerList = view.findViewById(R.id.llSellerList);
        LinearLayout llSentOrder = view.findViewById(R.id.llSentOrder);
        LinearLayout llResponseOrder = view.findViewById(R.id.llResponseOrder);
        LinearLayout llReceivedOrderRetailer = view.findViewById(R.id.llReceivedOrderRetailer);
        LinearLayout llTransaction = view.findViewById(R.id.llTransaction);
        LinearLayout llApprove = view.findViewById(R.id.llApprove);


        llApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ApproveActivity.class);
                startActivity(intent);
            }
        });

        llSellerList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), OwnerListAcivity.class);
                startActivity(intent);
            }
        });
        llSentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SentOrderActivity.class);
                startActivity(intent);
            }
        });

        llResponseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RespondedOrderActivity.class);
                startActivity(intent);
            }
        });


        llReceivedOrderRetailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReceivedOrderRetailerActivity.class);
                startActivity(intent);
            }
        });
        llTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TransactionsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}


