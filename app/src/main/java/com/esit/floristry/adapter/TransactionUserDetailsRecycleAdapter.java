package com.esit.floristry.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esit.floristry.R;
import com.esit.floristry.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionUserDetailsRecycleAdapter extends RecyclerView.Adapter<TransactionUserDetailsRecycleAdapter.TransactionUserDetailsViewHolder> {

    private List<Transaction> transactionList = new ArrayList<>();
    String transactionType;
    Context context;

    public TransactionUserDetailsRecycleAdapter(List<Transaction> transactions, String transactionType, Context context) {
        this.transactionList.addAll(transactions);
        this.transactionType = transactionType;
        this.context = context;
    }


    @Override
    public TransactionUserDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction_details_list, parent, false);
        return new TransactionUserDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionUserDetailsViewHolder holder, int position) {
        holder.setGenreTitle(transactionList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }


    public class TransactionUserDetailsViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSN;
        private TextView tvTransactionDate;
        private TextView tvAmount;


        public TransactionUserDetailsViewHolder(View itemView) {
            super(itemView);

            tvSN = (TextView) itemView.findViewById(R.id.tvSN);
            tvTransactionDate = (TextView) itemView.findViewById(R.id.tvTransactionDate);
            tvAmount = (TextView) itemView.findViewById(R.id.tvAmount);


        }

        public void setGenreTitle(Transaction transaction, int position) {
            int sn=position+1;
            tvSN.setText(sn+"");
            tvTransactionDate.setText(transaction.getTransactionDate());
            tvAmount.setText(transaction.getTransactionAmount() + "Tk");
        }
    }
}







