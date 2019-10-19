package com.esit.floristry.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esit.floristry.R;
import com.esit.floristry.activity.OrderDetailsActivity;
import com.esit.floristry.adapter.TransactionListRecycleViewAdapter;
import com.esit.floristry.model.Transaction;
import com.esit.floristry.storageUtility.AppSharedPreference;
import com.esit.floristry.utility.RecyclerClickListener;

import java.util.ArrayList;
import java.util.List;

public class RecentTransactionFragment extends Fragment {

    private RecyclerView recyclerView;

    Context context;
    List<Transaction> transactionList = new ArrayList<>();
    TransactionListRecycleViewAdapter transactionListRecycleViewAdapter;

    public RecentTransactionFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent_transaction, container, false);

        recyclerView = view.findViewById(R.id.recent_transaction_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        if (AppSharedPreference.getUserType(getContext()).equalsIgnoreCase("seller")) {

            transactionList = setTransactionItemList("seller");
            transactionListRecycleViewAdapter = new TransactionListRecycleViewAdapter(transactionList, "seller", getContext());
            recyclerView.setAdapter(transactionListRecycleViewAdapter);

        } else {
            transactionList = setTransactionItemList("retailer");
            transactionListRecycleViewAdapter = new TransactionListRecycleViewAdapter(transactionList, "retailer", getContext());
            recyclerView.setAdapter(transactionListRecycleViewAdapter);
        }


        // set order list data



        recyclerView.addOnItemTouchListener(new RecyclerClickListener(getActivity(), new RecyclerClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

//                Intent intent = new Intent(context, OrderDetailsActivity.class);
//                startActivity(intent);
            }
        }));


        return view;
    }

    private List<Transaction> setTransactionItemList(String userType) {
        List<Transaction> transactionList = new ArrayList<>();

        if (userType.equalsIgnoreCase("seller")){
            for (int x = 0; x < 4; x++) {

                Transaction transaction = null;
                switch (x) {

                    case 0:
                        transaction = new Transaction();
                        transaction.setOwnerName("Zahir Hossain");
                        transaction.setBusinessName("Pushpa Bazar Limited");
                        transaction.setTransactionDate("19 March 2019");
                        transaction.setTransactionId("39284 ");
                        transaction.setTransactionAmount("3500");
                        transaction.setTransactionType("seller");
                        break;
                    case 1:
                        transaction = new Transaction();
                        transaction.setOwnerName("Nahidul Islam");
                        transaction.setBusinessName("Flowers Online Shop");
                        transaction.setTransactionId("20321");
                        transaction.setTransactionDate("12 Dec 2018");
                        transaction.setTransactionAmount("5000");
                        transaction.setTransactionType("seller");
                        break;
                    case 2:
                        transaction = new Transaction();
                        transaction.setOwnerName("Zahir Uddin Rajon");
                        transaction.setBusinessName("Gift Shop BD");
                        transaction.setTransactionId("98765");
                        transaction.setTransactionDate("14 May 2019");
                        transaction.setTransactionAmount("4250");
                        transaction.setTransactionType("seller");
                        break;
                    case 3:
                        transaction = new Transaction();
                        transaction.setOwnerName("Shahadath Hossain");
                        transaction.setBusinessName("Colors Flower Shop");
                        transaction.setTransactionId("28853");
                        transaction.setTransactionDate("12 April 2019 ");
                        transaction.setTransactionAmount("2300");
                        transaction.setTransactionType("seller");
                        break;

                }
                transactionList.add(transaction);

            }
        }else{
            for (int x = 0; x < 3; x++) {

                Transaction transaction = null;
                switch (x) {

                    case 0:
                        transaction = new Transaction();
                        transaction.setOwnerName("Shahadath Hossain");
                        transaction.setBusinessName("Sahed Agro Flowers");
                        transaction.setTransactionDate("19 March 2019");
                        transaction.setTransactionId("39284 ");
                        transaction.setTransactionAmount("3500");
                        transaction.setTransactionType("seller");
                        break;
                    case 1:
                        transaction = new Transaction();
                        transaction.setOwnerName("Nahidul Islam");
                        transaction.setBusinessName("Flowers Agro Limited");
                        transaction.setTransactionId("20321");
                        transaction.setTransactionDate("12 Dec 2018");
                        transaction.setTransactionAmount("5000");
                        transaction.setTransactionType("seller");
                        break;
                    case 2:
                        transaction = new Transaction();
                        transaction.setOwnerName("Zahir Uddin Rajon");
                        transaction.setBusinessName("Pusho Agro Farm");
                        transaction.setTransactionId("98765");
                        transaction.setTransactionDate("14 May 2019");
                        transaction.setTransactionAmount("4250");
                        transaction.setTransactionType("seller");
                        break;


                }
                transactionList.add(transaction);

            }
        }

        return transactionList;
    }
}


