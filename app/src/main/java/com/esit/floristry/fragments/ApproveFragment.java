package com.esit.floristry.fragments;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.esit.floristry.R;
import com.esit.floristry.adapter.ProductsRecyclerAdapter;
import com.esit.floristry.adapter.TransactionListRecycleViewAdapter;
import com.esit.floristry.model.Product;
import com.esit.floristry.model.Transaction;
import com.esit.floristry.storageUtility.AppSharedPreference;
import com.esit.floristry.utility.RecyclerClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ApproveFragment extends Fragment {

    private RecyclerView recyclerView;
    String userType;
    ProgressDialog progressDialog;
    Context context;
    List<Transaction> transactionList = new ArrayList<>();
    TransactionListRecycleViewAdapter transactionListRecycleViewAdapter;
    Dialog dialog;

    FloatingActionButton fab;


    String businessName = "";
    String ownerName = "";
    String amount = "";

    public ApproveFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_approve, container, false);


        fab = view.findViewById(R.id.floating_action_button);
        recyclerView = view.findViewById(R.id.recent_transaction_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressDialog = new ProgressDialog(context);
        // progressDialog.setMessage("Please Wait");
        progressDialog.setMessage(context.getResources().getString(R.string.please_wait));
        dialog = new Dialog(context);


        if (AppSharedPreference.getUserType(getContext()).equalsIgnoreCase("seller")) {

            userType = "seller";
            fab.hide();
            transactionList = setTransactionItemList(userType);
            transactionListRecycleViewAdapter = new TransactionListRecycleViewAdapter(transactionList, userType, getContext());
            recyclerView.setAdapter(transactionListRecycleViewAdapter);

        } else {
            userType = "retailer";

            transactionList = setTransactionItemList(userType);
            transactionListRecycleViewAdapter = new TransactionListRecycleViewAdapter(transactionList, userType, getContext());
            recyclerView.setAdapter(transactionListRecycleViewAdapter);
        }


        // set order list data


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              final Dialog  dialogRequest = new Dialog(context);
                dialogRequest.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogRequest.setContentView(R.layout.new_approve_request_dialog);
                final Window window = dialogRequest.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                dialogRequest.setCancelable(true);


                Spinner spSellerList = dialogRequest.findViewById(R.id.spSellerList);
                final EditText etAmount = dialogRequest.findViewById(R.id.etAmount);
                final TextView tvBusinessName = dialogRequest.findViewById(R.id.tvBusinessName);
                final TextView tvOwnerName = dialogRequest.findViewById(R.id.tvOwnerName);
                final TextView tvAmount = dialogRequest.findViewById(R.id.tvAmount);
                TextView tvCancel = dialogRequest.findViewById(R.id.tvCancel);
                TextView tvAddRequest = dialogRequest.findViewById(R.id.tvAddRequest);

                // initially set all preview with ""
                tvBusinessName.setText("");
                tvOwnerName.setText("");
                tvAmount.setText("");
                etAmount.setText("");


                // Seller list Spinner
                final List<String> categories = new ArrayList<String>();
                categories.add("SELECT A SELLER");
                categories.add("Pushpa Agro House "); // zahir uddin Rajon
                categories.add("Flowers Agro Limited"); // Nahidul Islam
                categories.add("Bangladesh Flowers Agro"); // Shahadath Hossain
                categories.add("Green Garden BD"); // Tusher Hossain

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, categories);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSellerList.setAdapter(dataAdapter);
                spSellerList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        switch (position) {
                            case 0:
                                tvBusinessName.setText("Business Name");
                                tvOwnerName.setText("Owner Name");
                                tvAmount.setText("");
                                etAmount.setText("");


                                businessName = "";
                                ownerName = "";
                                amount = "";

                                break;
                            case 1:
                                tvBusinessName.setText("Pushpa Agro House");
                                tvOwnerName.setText("Owner: " + "zahir uddin Rajon");
                                tvAmount.setText("");
                                etAmount.setText("");

                                businessName = "Pushpa Agro House";
                                ownerName = "zahir uddin Rajon";


                                break;
                            case 2:
                                tvBusinessName.setText("Flowers Agro Limited");
                                tvOwnerName.setText("Owner: " + "Nahidul Islam");
                                tvAmount.setText("");
                                etAmount.setText("");

                                businessName = "Flowers Agro Limited";
                                ownerName = "Nahidul Islam";


                                break;
                            case 3:
                                tvBusinessName.setText("Bangladesh Flowers Agro");
                                tvOwnerName.setText("Owner: " + "Shahadath Hossain");
                                tvAmount.setText("");
                                etAmount.setText("");

                                businessName = "Bangladesh Flowers Agro";
                                ownerName = "Shahadath Hossain";

                                break;
                            case 4:
                                tvBusinessName.setText("Green Garden BD");
                                tvOwnerName.setText("Owner: " + "Tusher Hossain");
                                tvAmount.setText("");
                                etAmount.setText("");


                                businessName = "Green Garden BD";
                                ownerName = "Tusher Hossain";
                                break;
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                etAmount.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        if (s.length() > 0) {
                            tvAmount.setText("Payment Amount: " + s + " Tk");
                            amount = s + "";
                        } else {
                            tvAmount.setText("Payment Amount: " + 0 + " Tk");
                            amount = "";
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                // add product
                tvAddRequest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (amount.length() > 0 && ownerName.length() > 0 && businessName.length() > 0) {

                            progressDialog.show();

                            Transaction transaction = new Transaction();
                            transaction.setOwnerName(ownerName);
                            transaction.setBusinessName(businessName);
                            transaction.setTransactionId(new Random().nextInt(10000) + "");
                            transaction.setTransactionDate(getTodayDate());
                            transaction.setTransactionAmount(amount);
                            transaction.setTransactionType("seller");
                            transactionList.add(transaction);

                            transactionListRecycleViewAdapter = new TransactionListRecycleViewAdapter(transactionList, userType, getContext());
                            recyclerView.setAdapter(transactionListRecycleViewAdapter);
                            transactionListRecycleViewAdapter.notifyDataSetChanged();
                            dialogRequest.dismiss();

                            Handler handler = new Handler();
                            handler.postDelayed(delayProgress, 2000);

                        } else {

                        }


                    }
                });
                // cancel dialog
                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialogRequest.dismiss();
                    }
                });


                dialogRequest.show();

            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerClickListener(getActivity(), new RecyclerClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {


                // if seller , show approve dialog
                if (userType.equalsIgnoreCase("seller")) {

                    // show a dialog

                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.approve_dialog);
                    final Window window = dialog.getWindow();
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    window.setGravity(Gravity.CENTER);
                    dialog.setCancelable(true);
                    dialog.show();

                    TextView tvBusinessName = dialog.findViewById(R.id.tvBusinessName);
                    TextView tvOwnerName = dialog.findViewById(R.id.tvOwnerName);
                    TextView tvId = dialog.findViewById(R.id.tvId);
                    TextView tvDate = dialog.findViewById(R.id.tvDate);
                    TextView tvCancel = dialog.findViewById(R.id.tvCancel);
                    TextView tvApprove = dialog.findViewById(R.id.tvApprove);
                    TextView tvAmount = dialog.findViewById(R.id.tvAmount);


                    tvBusinessName.setText(transactionList.get(position).getBusinessName());
                    tvOwnerName.setText("Owner: " + transactionList.get(position).getOwnerName());
                    tvDate.setText("Date: " + transactionList.get(position).getTransactionDate());
                    tvId.setText("Transaction Id: #" + transactionList.get(position).getTransactionId());
                    tvAmount.setText(transactionList.get(position).getTransactionAmount() + " TK");

                    tvCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();
                        }
                    });
                    tvApprove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            // show progress and update list adapter
                            progressDialog.show();

                            //update the list
                            transactionList.remove(position);

                            Handler handler = new Handler();
                            handler.postDelayed(delayRunn, 2000);

                            //

                        }
                    });


                    // show dialog
                    dialog.show();

                } else {

                }


            }
        }));


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (userType.equalsIgnoreCase("retailer")) {
                    if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                        fab.hide();
                    } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                        fab.show();
                    }
                }

            }
        });


        return view;
    }

    private String getTodayDate() {
        String date = "";
        Calendar now = Calendar.getInstance();
        String month = getMonth(now.get(Calendar.MONTH) + 1);
        String year = now.get(Calendar.YEAR) + "";
        String day = now.get(Calendar.DAY_OF_MONTH) + "";

        date = day + " " + month + " " + year;

        return date;
    }

    private String getMonth(int i) {
        String month = "";
        switch (i) {
            case 1:
                month = "Jan";
                break;
            case 2:
                month = "Feb";
                break;
            case 3:
                month = "Mar";
                break;
            case 4:
                month = "Apr";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "Jun";
                break;
            case 7:
                month = "Jul";
                break;
            case 8:
                month = "Aug";
                break;
            case 9:
                month = "Sep";
                break;
            case 10:
                month = "Oct";
                break;
            case 11:
                month = "Nov";
                break;
            case 12:
                month = "Dec";
                break;
        }
        return month;
    }

    private List<Transaction> setTransactionItemList(String userType) {
        List<Transaction> transactionList = new ArrayList<>();

        if (userType.equalsIgnoreCase("seller")) {
            for (int x = 0; x < 2; x++) {

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
//                    case 2:
//                        transaction = new Transaction();
//                        transaction.setOwnerName("Zahir Hossain");
//                        transaction.setBusinessName("Pushpa Bazar Limited");
//                        transaction.setTransactionDate("19 March 2019");
//                        transaction.setTransactionId("39284 ");
//                        transaction.setTransactionAmount("3500");
//                        transaction.setTransactionType("seller");
//                        break;
//                    case 3:
//                        transaction = new Transaction();
//                        transaction.setOwnerName("Nahidul Islam");
//                        transaction.setBusinessName("Flowers Online Shop");
//                        transaction.setTransactionId("20321");
//                        transaction.setTransactionDate("12 Dec 2018");
//                        transaction.setTransactionAmount("5000");
//                        transaction.setTransactionType("seller");
//                        break;
//                    case 4:
//                        transaction = new Transaction();
//                        transaction.setOwnerName("Zahir Hossain");
//                        transaction.setBusinessName("Pushpa Bazar Limited");
//                        transaction.setTransactionDate("19 March 2019");
//                        transaction.setTransactionId("39284 ");
//                        transaction.setTransactionAmount("3500");
//                        transaction.setTransactionType("seller");
//                        break;
//                    case 5:
//                        transaction = new Transaction();
//                        transaction.setOwnerName("Nahidul Islam");
//                        transaction.setBusinessName("Flowers Online Shop");
//                        transaction.setTransactionId("20321");
//                        transaction.setTransactionDate("12 Dec 2018");
//                        transaction.setTransactionAmount("5000");
//                        transaction.setTransactionType("seller");
//                        break;
//                    case 6:
//                        transaction = new Transaction();
//                        transaction.setOwnerName("Zahir Hossain");
//                        transaction.setBusinessName("Pushpa Bazar Limited");
//                        transaction.setTransactionDate("19 March 2019");
//                        transaction.setTransactionId("39284 ");
//                        transaction.setTransactionAmount("3500");
//                        transaction.setTransactionType("seller");
//                        break;
//                    case 7:
//                        transaction = new Transaction();
//                        transaction.setOwnerName("Nahidul Islam");
//                        transaction.setBusinessName("Flowers Online Shop");
//                        transaction.setTransactionId("20321");
//                        transaction.setTransactionDate("12 Dec 2018");
//                        transaction.setTransactionAmount("5000");
//                        transaction.setTransactionType("seller");
//                        break;


                }
                transactionList.add(transaction);

            }
        } else {
            for (int x = 0; x < 2; x++) {

                Transaction transaction = null;
                switch (x) {


                    case 0:
                        transaction = new Transaction();
                        transaction.setOwnerName("Nahidul Islam");
                        transaction.setBusinessName("Flowers Agro Limited");
                        transaction.setTransactionId("20321");
                        transaction.setTransactionDate("12 Dec 2018");
                        transaction.setTransactionAmount("5000");
                        transaction.setTransactionType("seller");
                        break;
                    case 1:
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


    private final Runnable delayRunn = new Runnable() {

        @Override
        public void run() {

            transactionListRecycleViewAdapter = null;
            transactionListRecycleViewAdapter = new TransactionListRecycleViewAdapter(transactionList, userType, getContext());
            recyclerView.setAdapter(transactionListRecycleViewAdapter);
            transactionListRecycleViewAdapter.notifyDataSetChanged();
            Toast.makeText(context, "Transaction Approved successfully", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            if (dialog.isShowing())
                dialog.dismiss();

        }
    };

    private final Runnable delayProgress = new Runnable() {

        @Override
        public void run() {

            Toast.makeText(context, "Request sent successfully", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();


        }
    };
}




