package com.esit.floristry.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esit.floristry.R;
import com.esit.floristry.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerAdapter.OwnerViewHolder> {

    private List<Product> productList = new ArrayList<>();

    public ProductsRecyclerAdapter(List<Product> offers) {
        this.productList.addAll(offers);
    }

    @Override
    public OwnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_list, parent, false);
        return new OwnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OwnerViewHolder holder, int position) {
        holder.setGenreTitle(productList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class OwnerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSerialNumber;
        private TextView tvProductDetails;
        private TextView tvProductQuantity;

        public OwnerViewHolder(View itemView) {
            super(itemView);
            tvSerialNumber = (TextView) itemView.findViewById(R.id.tvSerialNumber);
            tvProductDetails = (TextView) itemView.findViewById(R.id.tvProductDetails);
            tvProductQuantity = (TextView) itemView.findViewById(R.id.tvProductQuantity);
        }

        public void setGenreTitle(Product product, int position) {
            String details = productDetails(product);
            int sn=position+1;
            tvSerialNumber.setText(sn + "");
            tvProductDetails.setText(details);
            tvProductQuantity.setText(product.getQuantity());
        }

        public String productDetails(Product product) {
            String details;
            if (product.getTotalCost().length() < 2) {
                details = product.getName() + "\t" +
                        product.getGrade();
            } else {
                details = product.getName() + "\t" +
                        product.getGrade() + "\t" + product.getUnitPrice() + "\t" + product.getTotalCost();
            }

            return details;
        }
    }
}




