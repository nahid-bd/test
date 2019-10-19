package com.esit.floristry.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.esit.floristry.R;
import com.esit.floristry.adapter.TransactionOwnerListRecycleAdapter;
import com.esit.floristry.adapter.TransactionUserDetailsRecycleAdapter;
import com.esit.floristry.model.Owner;
import com.esit.floristry.model.Transaction;
import com.esit.floristry.storageUtility.AppSharedPreference;
import com.esit.floristry.utility.RecyclerClickListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserWiseTransactionFragment extends Fragment {

    View rootView;

    static UserWiseTransactionFragment f;

    RecyclerView rvOwnerList;
    RecyclerView rvOwnerTransactionDetails;


    List<Owner> ownerList = new ArrayList<>();
    TransactionOwnerListRecycleAdapter OwnerListRecycleAdapter;


    List<Transaction> transactionList = new ArrayList<>();
    TransactionUserDetailsRecycleAdapter transactionDetailsRecycleAdapter;


//    SalesReportRecyclerAdapterCSMTSO salesReportRecyclerAdapterCSM;
//    SalesReportRecyclerAdapterCSM salesReportRecyclerAdapterCSMTSO;
//    ArrayList<ReportDTO> reportDTOs = new ArrayList<>();
//
//    ArrayList<ReportDTO> reportDTOsTSO = new ArrayList<>();

    LinearLayout lLOwnerTransactionDetails;
    LinearLayout lLBusinessOwnerList;

    TextView businessName;
    ImageView imgView_hide;
    TextView tvOwnerListTitle;

    String userType = "";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public static UserWiseTransactionFragment getInstance() {
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_transaction_user_based, container, false);

        lLBusinessOwnerList = rootView.findViewById(R.id.lLBusinessOwnerList);

        rvOwnerList = rootView.findViewById(R.id.rvOwnerList);

        tvOwnerListTitle = rootView.findViewById(R.id.tvOwnerListTitle);
        lLOwnerTransactionDetails = rootView.findViewById(R.id.lLOwnerTransactionDetails);
        rvOwnerTransactionDetails = rootView.findViewById(R.id.rvOwnerTransactionDetails);
        businessName = rootView.findViewById(R.id.txt_BusinessName);
        imgView_hide = rootView.findViewById(R.id.imgView_hide);


        imgView_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lLBusinessOwnerList.setVisibility(View.VISIBLE);
                lLOwnerTransactionDetails.setVisibility(View.GONE);
            }
        });


        if (AppSharedPreference.getUserType(getContext()).equalsIgnoreCase("seller")) {
            userType = "seller";
            tvOwnerListTitle.setText("RETAILER BASED");
            ownerList = getSellerList();


        } else {
            userType = "retailer";
            tvOwnerListTitle.setText("SELLER BASED");
            ownerList = getRetailerList();
        }
        OwnerListRecycleAdapter = new TransactionOwnerListRecycleAdapter(ownerList);
        rvOwnerList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOwnerList.setAdapter(OwnerListRecycleAdapter);


        rvOwnerList.addOnItemTouchListener(new RecyclerClickListener(getContext(), new RecyclerClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Show TSO layout
                // load data and show list

                lLBusinessOwnerList.setVisibility(View.GONE);
                lLOwnerTransactionDetails.setVisibility(View.VISIBLE);

                String businessTitle=ownerList.get(position).getBusinessName();
                businessName.setText(businessTitle);

                transactionList = getTransactionList();

                transactionDetailsRecycleAdapter = new TransactionUserDetailsRecycleAdapter(transactionList, userType, getContext());
                rvOwnerTransactionDetails.setLayoutManager(new LinearLayoutManager(getContext()));
                rvOwnerTransactionDetails.setAdapter(transactionDetailsRecycleAdapter);


            }
        }));


        //   new AsyncTaskSalesReport().execute("");


        return rootView;
    }

    private List<Transaction> getTransactionList() {

        List<Transaction> transactionList = new ArrayList<>();

        for (int x = 0; x < 6; x++) {

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
                    transaction.setTransactionDate("30 March 2019");
                    transaction.setTransactionAmount("5000");
                    transaction.setTransactionType("seller");
                    break;
                case 2:
                    transaction = new Transaction();
                    transaction.setOwnerName("Zahir Uddin Rajon");
                    transaction.setBusinessName("Pusho Agro Farm");
                    transaction.setTransactionId("98765");
                    transaction.setTransactionDate("12 April 2019");
                    transaction.setTransactionAmount("4250");
                    transaction.setTransactionType("seller");
                    break;

                case 3:
                    transaction = new Transaction();
                    transaction.setOwnerName("Shahadath Hossain");
                    transaction.setBusinessName("Sahed Agro Flowers");
                    transaction.setTransactionDate("28 April 2019");
                    transaction.setTransactionId("39284 ");
                    transaction.setTransactionAmount("2860");
                    transaction.setTransactionType("seller");
                    break;
                case 4:
                    transaction = new Transaction();
                    transaction.setOwnerName("Nahidul Islam");
                    transaction.setBusinessName("Flowers Agro Limited");
                    transaction.setTransactionId("20321");
                    transaction.setTransactionDate("08 May 2019");
                    transaction.setTransactionAmount("2700");
                    transaction.setTransactionType("seller");
                    break;
                case 5:
                    transaction = new Transaction();
                    transaction.setOwnerName("Zahir Uddin Rajon");
                    transaction.setBusinessName("Pusho Agro Farm");
                    transaction.setTransactionId("98765");
                    transaction.setTransactionDate("19 June 2019");
                    transaction.setTransactionAmount("6950");
                    transaction.setTransactionType("seller");
                    break;

            }
            transactionList.add(transaction);

        }
        return transactionList;
    }

    private List<Owner> getRetailerList() {
        List<Owner> retailerList = new ArrayList<>();


        for (int x = 0; x < 6; x++) {

            Owner owner = null;
            switch (x) {
                case 0:
                    owner = new Owner();
                    owner.setName("Nahidul Islam");
                    owner.setBusinessName("Nahid Agro Limited");
                    owner.setDistrict("Lakshmipur");
                    owner.setUpzila("Ramganj");
                    owner.setMobile("+8801712345678");
                    break;
                case 1:
                    owner = new Owner();
                    owner.setName("Zahir Uddin Rajon");
                    owner.setBusinessName("Pusho Agro House");
                    owner.setDistrict("Jessore");
                    owner.setUpzila("Godkhali");
                    owner.setMobile("+8801812345678");
                    break;
                case 2:
                    owner = new Owner();
                    owner.setName("Shahadath Hossain");
                    owner.setBusinessName("Sahed Agro Flowers");
                    owner.setDistrict("Bogra");
                    owner.setUpzila("Godagari ");
                    owner.setMobile("+8801987654321");
                    break;
                case 3:
                    owner = new Owner();
                    owner.setName("Nahidul Islam");
                    owner.setBusinessName("Flowers Agro Limited");
                    owner.setDistrict("Lakshmipur");
                    owner.setUpzila("Ramganj");
                    owner.setMobile("+8801712345678");
                    break;
                case 4:
                    owner = new Owner();
                    owner.setName("Zahir Uddin Rajon");
                    owner.setBusinessName("Pusho Agro Farm");
                    owner.setDistrict("Jessore");
                    owner.setUpzila("Godkhali");
                    owner.setMobile("+8801812345678");
                    break;
                case 5:
                    owner = new Owner();
                    owner.setName("Shahadath Hossain");
                    owner.setBusinessName("Sahed Agro Flowers");
                    owner.setDistrict("Bogra");
                    owner.setUpzila("Godagari ");
                    owner.setMobile("+8801987654321");
                    break;

            }
            retailerList.add(owner);

        }


        return retailerList;
    }

    private List<Owner> getSellerList() {
        List<Owner> sellerList = new ArrayList<>();

        for (int x = 0; x < 6; x++) {

            Owner owner = null;
            switch (x) {
                case 0:
                    owner = new Owner();
                    owner.setName("Chayan Debnath");
                    owner.setBusinessName("Pushpa Bazar Limited");
                    owner.setDistrict("Lakshmipur");
                    owner.setUpzila("Ramganj");
                    owner.setMobile("+8801712345678");
                    break;
                case 1:
                    owner = new Owner();
                    owner.setName("Zahir Uddin Rajon");
                    owner.setBusinessName("Colors Flower Shop");
                    owner.setDistrict("Jessore");
                    owner.setUpzila("Godkhali");
                    owner.setMobile("+8801812345678");
                    break;
                case 2:
                    owner = new Owner();
                    owner.setName("Shahadath Hossain");
                    owner.setBusinessName("Fresh Flowers Shop");
                    owner.setDistrict("Bogra");
                    owner.setUpzila("Godagari ");
                    owner.setMobile("+8801987654321");
                    break;
                case 3:
                    owner = new Owner();
                    owner.setName("Nahidul Islam");
                    owner.setBusinessName("Flowers Online BD");
                    owner.setDistrict("Lakshmipur");
                    owner.setUpzila("Ramganj");
                    owner.setMobile("+8801712345678");
                    break;
                case 4:
                    owner = new Owner();
                    owner.setName("Zahir Uddin Rajon");
                    owner.setBusinessName("Deshi Fuler Bitan");
                    owner.setDistrict("Jessore");
                    owner.setUpzila("Godkhali");
                    owner.setMobile("+8801812345678");
                    break;
                case 5:
                    owner = new Owner();
                    owner.setName("Shahadath Hossain");
                    owner.setBusinessName("Flower Gift Shop BD");
                    owner.setDistrict("Bogra");
                    owner.setUpzila("Godagari ");
                    owner.setMobile("+8801987654321");
                    break;

            }
            sellerList.add(owner);

        }

        return sellerList;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public String optStringNullCheck(final JSONObject json, final String key) {
        if (json.isNull(key) || json.optString(key).equalsIgnoreCase("null") || json.isNull(key) || json.optString(key).equalsIgnoreCase(""))
            return "0";
        else
            return json.optString(key, key);
    }

    public void noDataAlert(String message) {

        AlertDialog.Builder alertDialBuilder = new AlertDialog.Builder(getActivity());
        alertDialBuilder.setMessage(message);
        alertDialBuilder.setCancelable(false);
        alertDialBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        alertDialBuilder.create().show();

    }


//    @SuppressLint("StaticFieldLeak")
//    private class AsyncTaskSalesReport extends AsyncTask<String, String, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            RequestQueue scmRequestQueue = Volley.newRequestQueue(getContext());
////            String url = "http://192.168.15.77:8080/Api/sales/api/csm-asm-secondary-and-achivment?userId="
////                    + SharePreference.getUserId(getContext());
//            String url = "http://45.64.135.239:8080/Api/sales/api/csm-asm-secondary-and-achivment?userId="
//                    + SharePreference.getUserId(getContext());
//            StringRequest scmStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    try {
//
//
//                        JSONArray array = new JSONArray(response);
//                        ArrayList<ReportDTO> reportDTOList = new ArrayList<>();
//
//                        Log.v("_csm report:", array.toString());
//                        int length = array.length();
//                        for (int i = 0; i < length; i++) {
//                            JSONObject jsonObject = array.getJSONObject(i);
//
//                            ReportDTO reportDTO = new ReportDTO();
//                            reportDTO.setUserId(optStringNullCheck(jsonObject, "UserId"));
//                            reportDTO.setName(optStringNullCheck(jsonObject, "FullName"));
//                            reportDTO.setTarget(optStringNullCheck(jsonObject, "target"));
//                            reportDTO.setSales(optStringNullCheck(jsonObject, "Sale"));
//
//                            int sales = Integer.parseInt(optStringNullCheck(jsonObject, "Sale"));
//                            float target = Integer.parseInt(optStringNullCheck(jsonObject, "target"));
//                            float achieve = (sales * 100) / target;
//                            String achieveAsString = String.format("%.1f", achieve);
//                            Log.v("_csm report target:", achieveAsString + "" + " achieve: " + achieve + " sales: " + sales + " target: " + target);
//
//                            reportDTO.setAchieve(achieveAsString + " %");
//                            reportDTOList.add(reportDTO);
//                        }
//
//
//                        reportDTOs.clear();
//                        reportDTOs = reportDTOList;
//
//
//                        salesReportRecyclerAdapterCSM = new SalesReportRecyclerAdapterCSMTSO(reportDTOs, getContext());
//                        rvCSMSalesReportASM.setLayoutManager(new LinearLayoutManager(getContext()));
//                        rvCSMSalesReportASM.setAdapter(salesReportRecyclerAdapterCSM);
//                        salesReportRecyclerAdapterCSM.notifyDataSetChanged();
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                }
//            });
//
//            scmStringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 5, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            scmRequestQueue.getCache().clear();
//            scmRequestQueue.add(scmStringRequest);
//            return null;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//        }
//    }


//    @SuppressLint("StaticFieldLeak")
//    private class AsyncTaskSalesReportTSO extends AsyncTask<String, String, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            RequestQueue scmRequestQueue = Volley.newRequestQueue(getContext());
////            String url = "http://192.168.15.77:8080/Api/sales/api/csm-asm-secondary-and-achivment?userId="
////                    + SharePreference.getUserId(getContext());
//            String url = "http://45.64.135.239:8080/Api/sales/api/asm-tso-secondary-and-achivment?userId="
//                    + tsoId;
//            StringRequest scmStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    try {
//
//
//                        JSONArray array = new JSONArray(response);
//                        ArrayList<ReportDTO> reportDTOList = new ArrayList<>();
//
//                        Log.v("_csm report:", array.toString());
//                        int length = array.length();
//                        for (int i = 0; i < length; i++) {
//                            JSONObject jsonObject = array.getJSONObject(i);
//
//                            ReportDTO reportDTO = new ReportDTO();
//                            reportDTO.setUserId(optStringNullCheck(jsonObject, "TSPersonUserId"));
//                            reportDTO.setName(optStringNullCheck(jsonObject, "FullName"));
//                            reportDTO.setTarget(optStringNullCheck(jsonObject, "target"));
//                            reportDTO.setSales(optStringNullCheck(jsonObject, "Sale"));
//
//                            int sales = Integer.parseInt(optStringNullCheck(jsonObject, "Sale"));
//                            float target = Integer.parseInt(optStringNullCheck(jsonObject, "target"));
//                            float achieve = (sales * 100) / target;
//
//                            String achieveAsString = String.format ("%.1f", achieve);
//                            Log.v("_csm report target:", achieveAsString + ""+" achieve: "+achieve+" sales: "+sales+" target: "+target);
//
//                            reportDTO.setAchieve(achieveAsString + " %");
//                            reportDTOList.add(reportDTO);
//                        }
//
//
//                        reportDTOsTSO.clear();
//                        reportDTOsTSO=reportDTOList;
//
//
//                        salesReportRecyclerAdapterCSMTSO = new SalesReportRecyclerAdapterCSM(reportDTOsTSO, getContext());
//                        rvCSMSalesReportTSO.setLayoutManager(new LinearLayoutManager(getContext()));
//                        rvCSMSalesReportTSO.setAdapter(salesReportRecyclerAdapterCSMTSO);
//                        salesReportRecyclerAdapterCSMTSO.notifyDataSetChanged();
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                }
//            });
//
//            scmStringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 5, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            scmRequestQueue.getCache().clear();
//            scmRequestQueue.add(scmStringRequest);
//            return null;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//        }
//    }


}




