package com.esit.floristry.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esit.floristry.R;
import com.esit.floristry.model.Owner;

import java.util.ArrayList;
import java.util.List;

public class TransactionOwnerListRecycleAdapter extends RecyclerView.Adapter<TransactionOwnerListRecycleAdapter.OwnerViewHolder> {

    private List<Owner> ownerList = new ArrayList<>();

    public TransactionOwnerListRecycleAdapter(List<Owner> owners) {
        this.ownerList.addAll(owners);
    }

    @Override
    public OwnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction_list_owner, parent, false);
        return new OwnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OwnerViewHolder holder, int position) {
        holder.setGenreTitle(ownerList.get(position));
    }

    @Override
    public int getItemCount() {
        return ownerList.size();
    }


    public class OwnerViewHolder extends RecyclerView.ViewHolder {
        private TextView businessName;
        private TextView ownerName;
        private ImageView ivOwnerTransactionDetails;

        public OwnerViewHolder(View itemView) {
            super(itemView);
            businessName = (TextView) itemView.findViewById(R.id.businessName);
            ownerName = (TextView) itemView.findViewById(R.id.ownerName);
            ivOwnerTransactionDetails = (ImageView) itemView.findViewById(R.id.ivOwnerTransactionDetails);
        }

        public void setGenreTitle(Owner owner) {
            businessName.setText(owner.getBusinessName());
            ownerName.setText(owner.getName());

        }
    }
}




