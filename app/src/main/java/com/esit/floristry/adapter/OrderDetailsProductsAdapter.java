package com.esit.floristry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.esit.floristry.R;
import com.esit.floristry.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsProductsAdapter extends RecyclerView.Adapter<OrderDetailsProductsAdapter.OrderDetailsViewHolder> {

    private List<Product> productList = new ArrayList<>();
    Context mContext;

    public OrderDetailsProductsAdapter(List<Product> offers, Context context) {
        this.productList.addAll(offers);
        mContext = context;
    }

    @Override
    public OrderDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_details_product_list, parent, false);
        return new OrderDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderDetailsViewHolder holder, int position) {


        if (position==productList.size()){
            holder.setGenreTitle(new Product(), position, holder);
        }else{
            holder.setGenreTitle(productList.get(position), position, holder);
        }



    }

    @Override
    public int getItemCount() {
        return productList.size() + 1;
    }


    public class OrderDetailsViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSerialNumber;
        private TextView tvProductDetails;
        private TextView tvProductQuantity;
        LinearLayout llListItem;
        LinearLayout llSN;
        LinearLayout llProductInfo;
        LinearLayout llCost;

        public OrderDetailsViewHolder(View itemView) {
            super(itemView);
            llListItem = (LinearLayout) itemView.findViewById(R.id.llListItem);

            llSN = (LinearLayout) itemView.findViewById(R.id.llSN);
            llProductInfo = (LinearLayout) itemView.findViewById(R.id.llProductInfo);
            llCost = (LinearLayout) itemView.findViewById(R.id.llCost);

            tvSerialNumber = (TextView) itemView.findViewById(R.id.tvSerialNumber);
            tvProductDetails = (TextView) itemView.findViewById(R.id.tvProductDetails);
            tvProductQuantity = (TextView) itemView.findViewById(R.id.tvProductQuantity);
        }

        public void setGenreTitle(Product product, int position, OrderDetailsViewHolder holder) {

            if (position == productList.size()) {

                holder.llListItem.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));

                holder.llSN.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
                holder.llProductInfo.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
                holder.llCost.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));

                String details = "TOTAL COST";
                tvSerialNumber.setText("");
                tvProductDetails.setText(details);
                tvProductDetails.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                tvProductQuantity.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                tvProductQuantity.setText(getTotalCost(productList));
            } else {
                String details = productDetails(product);
                int sn = position + 1;
                tvSerialNumber.setText(sn + "");
                tvProductDetails.setText(details);
                tvProductQuantity.setText(product.getTotalCost());
                tvProductDetails.setTextColor(mContext.getResources().getColor(R.color.colorBlack_50));
                tvProductQuantity.setTextColor(mContext.getResources().getColor(R.color.colorBlack_50));

                holder.llListItem.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
                holder.llSN.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
                holder.llProductInfo.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
                holder.llCost.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
            }


        }

        public String productDetails(Product product) {
            String details;
            if (product.getTotalCost().length() < 2) {
                details = product.getName() + "\n" +
                        product.getGrade();
            } else {
                details = product.getName() + "\n" +
                        product.getGrade() + "  " + "Unit price: " + product.getUnitPrice();
            }

            return details;
        }
    }

    private String getTotalCost(List<Product> products) {
        String totalCost = "";
        Double total = 0.0;
        for (int x = 0; x < products.size(); x++) {
            Double tCost = Double.parseDouble(products.get(x).getTotalCost());
            total += tCost;

        }
        totalCost = total + "";
        return totalCost;
    }
}






