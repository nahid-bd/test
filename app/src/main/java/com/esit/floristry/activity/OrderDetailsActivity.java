package com.esit.floristry.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.esit.floristry.MainActivity;
import com.esit.floristry.R;
import com.esit.floristry.adapter.OrderDetailsProductsAdapter;
import com.esit.floristry.model.Product;
import com.esit.floristry.storageUtility.AppSharedPreference;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {

    public Toolbar mToolbar;
    TextView tvHomeToolbarTitle;
    LinearLayout llBack;

    Context context;
    RecyclerView rlAddedOrderList;
    TextView tvDeliveryDate;
    TextView tvAddProduct;
    TextView tvEditProduct;
    TextView tvDeleteProduct;
    List<Product> productList = new ArrayList<>();
    OrderDetailsProductsAdapter productsRecyclerAdapter;


    LinearLayout llSubmit;
    ProgressDialog progressDialog;
    String orderType = "";

    LinearLayout llCancel;
    LinearLayout llDone;
    TextView tvCancel;
    TextView tvDone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);


        context = this;
        progressDialog = new ProgressDialog(context);
        // progressDialog.setMessage("Please Wait");
        progressDialog.setMessage(context.getResources().getString(R.string.please_wait));


        initializeViews();
        if (getIntent() != null && getIntent().getStringExtra("fragment_type") != null) {
            orderType = getIntent().getStringExtra("fragment_type");
        }

        if (orderType.equalsIgnoreCase("claim")) {
            if (AppSharedPreference.getUserType(context).equalsIgnoreCase("seller")) {
                llSubmit.setVisibility(View.VISIBLE);
                llCancel.setVisibility(View.GONE);
                tvDone.setText("ADJUST CLAIM");
            } else {
                llSubmit.setVisibility(View.GONE);
            }

        } else if (orderType.equalsIgnoreCase("requested")) {
            if (AppSharedPreference.getUserType(context).equalsIgnoreCase("seller")) {
                llSubmit.setVisibility(View.VISIBLE);
                tvCancel.setText("SUBMIT");
                tvDone.setText("CHANGE");
            } else {
                llSubmit.setVisibility(View.GONE);
            }

        } else if (orderType.equalsIgnoreCase("confirmed")) {
            if (AppSharedPreference.getUserType(context).equalsIgnoreCase("seller")) {
                llSubmit.setVisibility(View.VISIBLE);
                tvCancel.setText("CANCEL");
                tvDone.setText("SHIFT");
            } else {
                llSubmit.setVisibility(View.GONE);
            }

        } else if (orderType.equalsIgnoreCase("shifted")) {
            if (AppSharedPreference.getUserType(context).equalsIgnoreCase("retailer")) {
                llSubmit.setVisibility(View.VISIBLE);
                llCancel.setVisibility(View.GONE);
                tvDone.setText("RECEIVE ORDER");
            } else {
                llSubmit.setVisibility(View.GONE);
            }

        } else if (orderType.equalsIgnoreCase("received")) {
            if (AppSharedPreference.getUserType(context).equalsIgnoreCase("retailer")) {
                llSubmit.setVisibility(View.VISIBLE);
                llCancel.setVisibility(View.GONE);
                tvDone.setText("CLAIM ORDER");
            } else {
                llSubmit.setVisibility(View.GONE);
            }

        } else if (orderType.equalsIgnoreCase("updated")) {
            llSubmit.setVisibility(View.VISIBLE);
            tvCancel.setText("CANCEL");
            tvDone.setText("CONFIRM");


        } else {
            llSubmit.setVisibility(View.GONE);

        }


        initializeClickListeners();
        rlAddedOrderList.setLayoutManager(new LinearLayoutManager(context));

        productList = getProductList();
        productsRecyclerAdapter = new OrderDetailsProductsAdapter(productList, context);
        rlAddedOrderList.setAdapter(productsRecyclerAdapter);
        productsRecyclerAdapter.notifyDataSetChanged();

    }

    private List<Product> getProductList() {

        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setId("00123");
        product.setName("White Jerbara(20)");
        product.setGrade(" Grade : A");
        product.setQuantity("55");
        product.setUnitPrice("3");
        product.setTotalCost("1550.00");
        productList.add(product);


        Product product1 = new Product();
        product1.setId("00156");
        product1.setName("Local Dutch Rose(50)");
        product1.setGrade(" Grade : A");
        product1.setQuantity("150");
        product1.setUnitPrice("5");
        product1.setTotalCost("750.00");
        productList.add(product1);

        Product product2 = new Product();
        product2.setId("00156");
        product2.setName("Local Dutch Rose(50)");
        product2.setGrade(" Grade : A");
        product2.setQuantity("100");
        product2.setUnitPrice("7");
        product2.setTotalCost("700.00");
        productList.add(product2);

        productList.add(product);
        productList.add(product1);
        productList.add(product2);
        productList.add(product);
        productList.add(product1);
        productList.add(product2);
        productList.add(product);
        productList.add(product1);
        productList.add(product2);


        return productList;
    }

    private void initializeClickListeners() {
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        llDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(orderDetailsDelayRun, 2000);
            }
        });

        llCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tvCancel.getText().toString().equalsIgnoreCase("change")) {
                    Intent intent = new Intent(context, NewOrderActivity.class);
                    intent.putExtra("edit_order",true);
                    startActivity(intent);
                } else {
                    progressDialog.show();
                    Handler handler = new Handler();
                    handler.postDelayed(orderDetailsDelayRun, 2000);
                }


            }
        });


    }


    private void initializeViews() {
        mToolbar = (Toolbar) findViewById(R.id.tbToolbarback);
        tvHomeToolbarTitle = (TextView) mToolbar.findViewById(R.id.tvHomeToolbarTitle);
        tvHomeToolbarTitle.setText("ORDER DETAILS");
        llBack = mToolbar.findViewById(R.id.llBack);

        rlAddedOrderList = findViewById(R.id.rlAddedOrderList);
        llSubmit = findViewById(R.id.llSubmit);

        llCancel = findViewById(R.id.llCancel);
        llDone = findViewById(R.id.llDone);

        tvCancel = findViewById(R.id.tvCancel);
        tvDone = findViewById(R.id.tvDone);


    }

    private final Runnable orderDetailsDelayRun = new Runnable() {

        @Override
        public void run() {
            progressDialog.dismiss();
            finish();

        }
    };
}


