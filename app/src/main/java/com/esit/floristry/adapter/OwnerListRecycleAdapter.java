package com.esit.floristry.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esit.floristry.R;
import com.esit.floristry.model.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerListRecycleAdapter extends RecyclerView.Adapter<OwnerListRecycleAdapter.OwnerViewHolder> {

    private List<Owner> ownerList = new ArrayList<>();

    public OwnerListRecycleAdapter(List<Owner> offers) {
        this.ownerList.addAll(offers);
    }

    @Override
    public OwnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_owner, parent, false);
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
        private TextView mobile;

        public OwnerViewHolder(View itemView) {
            super(itemView);
            businessName = (TextView) itemView.findViewById(R.id.businessName);
            ownerName = (TextView) itemView.findViewById(R.id.ownerName);
            mobile = (TextView) itemView.findViewById(R.id.mobile);
        }

        public void setGenreTitle(Owner owner) {
            businessName.setText(owner.getBusinessName());
            ownerName.setText(owner.getName());
            mobile.setText("Mobile: "+owner.getMobile());
        }
    }
}


