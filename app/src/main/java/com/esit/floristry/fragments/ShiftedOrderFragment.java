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
import com.esit.floristry.adapter.OrderListRecycleViewAdapter;
import com.esit.floristry.model.OrderItem;
import com.esit.floristry.utility.RecyclerClickListener;

import java.util.ArrayList;
import java.util.List;

public class ShiftedOrderFragment extends Fragment {

    private RecyclerView recyclerView;
    Context context;
    List<OrderItem> orderItemList = new ArrayList<>();
    OrderListRecycleViewAdapter orderListRecycleViewAdapter;

    public ShiftedOrderFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shifted_order, container, false);

        recyclerView = view.findViewById(R.id.shifted_order_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        // set order list data
        orderItemList=setOrderItemList();
        orderListRecycleViewAdapter=new OrderListRecycleViewAdapter(orderItemList,"shifted_order",getContext());
        recyclerView.setAdapter(orderListRecycleViewAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerClickListener(getActivity(), new RecyclerClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("fragment_type","shifted");

                startActivity(intent);
            }
        }));

        return view;
    }

    private List<OrderItem> setOrderItemList() {
        List<OrderItem> orderItemList = new ArrayList<>();

        for (int x=0;x<5;x++){

            OrderItem orderItem=null;
            switch (x){
                case 0:
                    orderItem=new OrderItem();
                    orderItem.setOwnerName("Nahidul Islam");
                    orderItem.setBusinessName("Nahid Agro Limited");
                    orderItem.setDeliveryDate("12 Jan 2019");
                    orderItem.setOrderId("48556");
                    orderItem.setOrderDate("01 Jan 2019");
                    orderItem.setStatusInfo("17 Jan 2019");
                    break;
                case 1:
                    orderItem=new OrderItem();
                    orderItem.setOwnerName("Zahir Uddin Rajon");
                    orderItem.setBusinessName("Pusho Agro House");
                    orderItem.setDeliveryDate("18 Feb 2019");
                    orderItem.setOrderId("49273");
                    orderItem.setOrderDate("04 Feb 2019");
                    orderItem.setStatusInfo("16 Feb 2019");
                    break;
                case 2:
                    orderItem=new OrderItem();
                    orderItem.setOwnerName("Shahadath Hossain");
                    orderItem.setBusinessName("Sahed Agro Flowers");
                    orderItem.setOrderDate("19 March 2019");
                    orderItem.setOrderId("39284 ");
                    orderItem.setDeliveryDate("01 April 2019");
                    orderItem.setStatusInfo("20 March 2019");
                    break;
                case 3:
                    orderItem=new OrderItem();
                    orderItem.setOwnerName("Nahidul Islam");
                    orderItem.setBusinessName("Flowers Agro Limited");
                    orderItem.setOrderId("20321");
                    orderItem.setOrderDate("12 Dec 2018");
                    orderItem.setDeliveryDate("02 Feb 2019");
                    orderItem.setStatusInfo("18 Jan 2019");
                    break;
                case 4:
                    orderItem=new OrderItem();
                    orderItem.setOwnerName("Zahir Uddin Rajon");
                    orderItem.setBusinessName("Pusho Agro Farm");
                    orderItem.setOrderId("98765");
                    orderItem.setOrderDate("14 May 2019");
                    orderItem.setDeliveryDate("21 June 2019");
                    orderItem.setStatusInfo("12 June 2019");
                    break;
//                case 5:
//                    orderItem=new OrderItem();
//                    orderItem.setOwnerName("Shahadath Hossain");
//                    orderItem.setBusinessName("Sahed Agro Flowers");
//                    orderItem.setOrderId("28853");
//                    orderItem.setOrderDate("12 April 2019 ");
//                    orderItem.setDeliveryDate(" 09 May 2019");
//                    orderItem.setStatusInfo("");
//                    break;

            }
            orderItemList.add(orderItem);

        }
        return orderItemList;
    }
}

