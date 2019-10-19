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

public class TransactionListRecycleViewAdapter extends RecyclerView.Adapter<TransactionListRecycleViewAdapter.TransactionListViewHolder> {

    private List<Transaction> transactionList = new ArrayList<>();
    String transactionType;
    Context context;

    public TransactionListRecycleViewAdapter(List<Transaction> transactions, String transactionType, Context context) {
        this.transactionList.addAll(transactions);
        this.transactionType = transactionType;
        this.context = context;
    }


    @Override
    public TransactionListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction_list, parent, false);
        return new TransactionListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionListViewHolder holder, int position) {
        holder.setGenreTitle(transactionList.get(position));
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }


    public class TransactionListViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTransactionId;
        private TextView tvTransactionDate;
        private TextView tvTransactionBusinessName;
        private TextView tvTransactionBusinessOwnerName;
        private TextView tvTransactionAmount;

        public TransactionListViewHolder(View itemView) {
            super(itemView);

            tvTransactionId = (TextView) itemView.findViewById(R.id.tvTransactionId);
            tvTransactionDate = (TextView) itemView.findViewById(R.id.tvTransactionDate);
            tvTransactionBusinessName = (TextView) itemView.findViewById(R.id.tvTransactionBusinessName);

            tvTransactionBusinessOwnerName = (TextView) itemView.findViewById(R.id.tvTransactionBusinessOwnerName);
            tvTransactionAmount = (TextView) itemView.findViewById(R.id.tvTransactionAmount);

        }

        public void setGenreTitle(Transaction transaction) {
            tvTransactionId.setText("Transaction ID: #" + transaction.getTransactionId());
            tvTransactionDate.setText("Date: " + transaction.getTransactionDate());
            tvTransactionBusinessName.setText(transaction.getBusinessName());
            tvTransactionBusinessOwnerName.setText(transaction.getOwnerName());
            if (transaction.getTransactionType().equalsIgnoreCase("seller")){
                tvTransactionAmount.setText("+ "+transaction.getTransactionAmount() + " Tk");
            }else{
                tvTransactionAmount.setText("- "+transaction.getTransactionAmount() + " Tk");
            }



        }
    }
}





