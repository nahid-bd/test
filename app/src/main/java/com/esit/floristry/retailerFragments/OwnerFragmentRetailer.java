package com.esit.floristry.retailerFragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.esit.floristry.R;
import com.esit.floristry.activity.NewOrderActivity;
import com.esit.floristry.adapter.OwnerListRecycleAdapter;
import com.esit.floristry.model.Owner;
import com.esit.floristry.utility.RecyclerClickListener;

import java.util.ArrayList;
import java.util.List;

public class OwnerFragmentRetailer extends Fragment {

    private RecyclerView recyclerView;
    Context context;
    OwnerListRecycleAdapter ownerListRecycleAdapter;
    List<Owner> owners = new ArrayList<>();

    public OwnerFragmentRetailer() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_owner_retailer, container, false);

        context = getContext();
        recyclerView = view.findViewById(R.id.rvOwnerListRetailer);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        // set owner lists
        owners = setOwnerLists();
        ownerListRecycleAdapter = new OwnerListRecycleAdapter(owners);
        recyclerView.setAdapter(ownerListRecycleAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerClickListener(getActivity(), new RecyclerClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                // show a dialog
                // show a dialog
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.owner_details_retailer);
                final Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                dialog.setCancelable(true);
                dialog.show();

                TextView tvBusinessName = dialog.findViewById(R.id.tvBusinessName);
                TextView tvOwnerName = dialog.findViewById(R.id.tvOwnerName);
                TextView tvMobile = dialog.findViewById(R.id.tvMobile);
                TextView tvDistrict = dialog.findViewById(R.id.tvDistrict);
                TextView tvUpzila = dialog.findViewById(R.id.tvUpzila);
                TextView tvOrder = dialog.findViewById(R.id.tvOrder);
                TextView tvCall = dialog.findViewById(R.id.tvCall);
                LinearLayout llDialogDismiss=dialog.findViewById(R.id.llDialogDismiss);

                tvBusinessName.setText(owners.get(position).getBusinessName());
                tvOwnerName.setText("Owner: "+owners.get(position).getName());
                tvMobile.setText("Mobile: "+owners.get(position).getMobile());
                tvDistrict.setText("District: "+owners.get(position).getDistrict());
                tvUpzila.setText("Upzila: "+owners.get(position).getUpzila());
                tvOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //close dialog
                        // open New order activity
                        dialog.dismiss();
                        Intent intent=new Intent(getContext(), NewOrderActivity.class);
                        startActivity(intent);



                    }
                });
                tvCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                llDialogDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        }));

        return view;
    }

    private List<Owner> setOwnerLists() {

        List<Owner> ownerList = new ArrayList<>();

        for (int x=0;x<6;x++){

            Owner owner=null;
            switch (x){
                case 0:
                    owner=new Owner();
                    owner.setName("Nahidul Islam");
                    owner.setBusinessName("Nahid Agro Limited");
                    owner.setDistrict("Lakshmipur");
                    owner.setUpzila("Ramganj");
                    owner.setMobile("+8801712345678");
                    break;
                case 1:
                    owner=new Owner();
                    owner.setName("Zahir Uddin Rajon");
                    owner.setBusinessName("Pusho Agro House");
                    owner.setDistrict("Jessore");
                    owner.setUpzila("Godkhali");
                    owner.setMobile("+8801812345678");
                    break;
                case 2:
                    owner=new Owner();
                    owner.setName("Shahadath Hossain");
                    owner.setBusinessName("Sahed Agro Flowers");
                    owner.setDistrict("Bogra");
                    owner.setUpzila("Godagari ");
                    owner.setMobile("+8801987654321");
                    break;
                case 3:
                    owner=new Owner();
                    owner.setName("Nahidul Islam");
                    owner.setBusinessName("Flowers Agro Limited");
                    owner.setDistrict("Lakshmipur");
                    owner.setUpzila("Ramganj");
                    owner.setMobile("+8801712345678");
                    break;
                case 4:
                    owner=new Owner();
                    owner.setName("Zahir Uddin Rajon");
                    owner.setBusinessName("Pusho Agro Farm");
                    owner.setDistrict("Jessore");
                    owner.setUpzila("Godkhali");
                    owner.setMobile("+8801812345678");
                    break;
                case 5:
                    owner=new Owner();
                    owner.setName("Shahadath Hossain");
                    owner.setBusinessName("Sahed Agro Flowers");
                    owner.setDistrict("Bogra");
                    owner.setUpzila("Godagari ");
                    owner.setMobile("+8801987654321");
                    break;

            }
            ownerList.add(owner);

        }

        return ownerList;
    }
}




