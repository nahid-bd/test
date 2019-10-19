package com.esit.floristry.activity;

import android.app.Dialog;
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
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.esit.floristry.MainActivity;
import com.esit.floristry.R;
import com.esit.floristry.adapter.ProductsRecyclerAdapter;
import com.esit.floristry.model.Product;
import com.esit.floristry.storageUtility.AppSharedPreference;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewOrderActivity extends AppCompatActivity {

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
    ProductsRecyclerAdapter productsRecyclerAdapter;

    List<String> productTitleList = new ArrayList<String>();
    ArrayAdapter<String> productTitleListAdapter;

    String category = "";
    String title = "";
    String grade = "";


    LinearLayout llSubmit;
    ProgressDialog progressDialog;

    boolean isEditOrder = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        context = this;
        progressDialog = new ProgressDialog(context);
        // progressDialog.setMessage("Please Wait");
        progressDialog.setMessage(context.getResources().getString(R.string.please_wait));

        isEditOrder = getIntent().getBooleanExtra("edit_order", false);


        initializeViews();
        initializeClickListeners();
        rlAddedOrderList.setLayoutManager(new LinearLayoutManager(context));


        productList = getProductList();
        productsRecyclerAdapter = new ProductsRecyclerAdapter(productList);
        rlAddedOrderList.setAdapter(productsRecyclerAdapter);

        productsRecyclerAdapter.notifyDataSetChanged();


    }

    private List<Product> getProductList() {

        List<Product> productList = new ArrayList<>();

//        Product product = new Product();
//        product.setId("00123");
//        product.setName("White Jerbara(20)");
//        product.setGrade(" Grade : A");
//        product.setQuantity("15");
//        product.setUnitPrice("");
//        product.setTotalCost("");
//        productList.add(product);
//
//
//        Product product1 = new Product();
//        product1.setId("00156");
//        product1.setName("Local Dutch Rose(50)");
//        product1.setGrade(" Grade : A");
//        product1.setQuantity("13");
//        product1.setUnitPrice("");
//        product1.setTotalCost("");
//        productList.add(product1);


        return productList;
    }

    private void initializeClickListeners() {
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tvDeliveryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        llSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(delayRunn, 3500);
            }
        });

        tvAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show add product dialog
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.add_product_dialog);
                final Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                dialog.setCancelable(true);


                Spinner spProductCategory = dialog.findViewById(R.id.spProductCategory);
                Spinner spProductTitle = dialog.findViewById(R.id.spProductTitle);
                Spinner spGrade = dialog.findViewById(R.id.spGrade);
                final EditText etQuantity = dialog.findViewById(R.id.etQuantity);
                TextView tvProductTitlePreview = dialog.findViewById(R.id.tvProductTitlePreview);
                TextView tvGradePreview = dialog.findViewById(R.id.tvGradePreview);
                TextView tvQuantityPreview = dialog.findViewById(R.id.tvQuantityPreview);
                TextView tvCancel = dialog.findViewById(R.id.tvCancel);
                TextView tvAddProduct = dialog.findViewById(R.id.tvAddProduct);

                // initially set all preview with ""
                tvProductTitlePreview.setText("");
                tvGradePreview.setText("");
                tvQuantityPreview.setText("");
                etQuantity.setText("");


                // category Spinner
                final List<String> categories = new ArrayList<String>();
                categories.add("SELECT CATEGORY");
                categories.add("Tube Rose ");
                categories.add("Gladiolas");
                categories.add("Marigolds");
                categories.add("Jerbara");

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, categories);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spProductCategory.setAdapter(dataAdapter);
                spProductCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        productTitleList = getProductTitleList(position);
                        productTitleListAdapter.clear();
                        productTitleListAdapter.addAll(productTitleList);
                        productTitleListAdapter.notifyDataSetChanged();

                        if (position != 0) {
                            category = categories.get(position);
                        } else {
                            category = "";
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                // ProductTitle Spinner

                productTitleList.add("SELECT PRODUCT");
                productTitleListAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, productTitleList);
                productTitleListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spProductTitle.setAdapter(productTitleListAdapter);
                spProductTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        title = productTitleList.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                // grade spinner
                final List<String> gradeList = new ArrayList<String>();
                gradeList.add("SELECT GRADE");
                gradeList.add("GRADE A ");
                gradeList.add("GRADE B");
                gradeList.add("GRADE C");
                gradeList.add("GRADE D");
                ArrayAdapter<String> gradeListAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, gradeList);
                gradeListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spGrade.setAdapter(gradeListAdapter);
                spGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        grade = gradeList.get(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                // add product
                tvAddProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (category.length() > 0 && title.length() > 0) {
                            // add product to product list in recycle view
                            Product product2 = new Product();
                            product2.setId("" + Math.random());
                            product2.setName(category + ": " + title);
                            product2.setGrade(grade);
                            product2.setQuantity(etQuantity.getText().toString());
                            product2.setUnitPrice("");
                            product2.setTotalCost("");
                            productList.add(product2);
                            productsRecyclerAdapter = null;
                            productsRecyclerAdapter = new ProductsRecyclerAdapter(productList);
                            rlAddedOrderList.setAdapter(productsRecyclerAdapter);
                            productsRecyclerAdapter.notifyDataSetChanged();

                            dialog.dismiss();
                        }


                    }
                });
                // cancel dialog
                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });


                dialog.show();

            }
        });
        tvEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private List<String> getProductTitleList(int position) {
        List<String> productTitleList = new ArrayList<String>();
        if (position == 0) {
            productTitleList.clear();
            productTitleList.add("SELECT PRODUCT");


        } else {
            if (position == 1) {
                // list of tube rose
                productTitleList.clear();
                productTitleList.add("Hybrid Aloka (50)");
                productTitleList.add("Vutta Tube Rose (50)");


            } else if (position == 2) {
                // list of Gladiolas
                productTitleList.clear();
                productTitleList.add("White Gladiolas (50)");
                productTitleList.add("Pink Gladiolas (50)");
                productTitleList.add("Yellow Gladiolas (50)");
                productTitleList.add("Purple Gladiolas (50)");
                productTitleList.add("Mixed Gladiolas (50)");


            } else if (position == 3) {
                // list of Marigolds
                productTitleList.clear();
                productTitleList.add("White Marigolds (20)");
                productTitleList.add("Pink Marigolds (20)");


            } else if (position == 4) {
                // list of Jerbara
                productTitleList.clear();
                productTitleList.add("White Jerbara (20)");
                productTitleList.add("Pink Jerbara (20)");
                productTitleList.add("Yellow Jerbara (20)");
                productTitleList.add("Purple Jerbara (20)");
                productTitleList.add("Mixed Jerbara (20)");


            }
        }
        return productTitleList;
    }

    private void initializeViews() {
        mToolbar = (Toolbar) findViewById(R.id.tbToolbarback);
        tvHomeToolbarTitle = (TextView) mToolbar.findViewById(R.id.tvHomeToolbarTitle);
        if (isEditOrder) {
            tvHomeToolbarTitle.setText("CHANGE ORDER");
        } else {
            tvHomeToolbarTitle.setText("NEW ORDER");
        }
        llBack = mToolbar.findViewById(R.id.llBack);

        rlAddedOrderList = findViewById(R.id.rlAddedOrderList);
        tvDeliveryDate = findViewById(R.id.tvDeliveryDate);
        tvAddProduct = findViewById(R.id.tvAddProduct);
        tvEditProduct = findViewById(R.id.tvEditProduct);
        tvDeleteProduct = findViewById(R.id.tvDeleteProduct);
        llSubmit = findViewById(R.id.llSubmit);


    }

    private final Runnable delayRunn = new Runnable() {

        @Override
        public void run() {
            Toast.makeText(context, "Order Submitted successfully", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            finish();

        }
    };
}
