package com.esit.floristry.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esit.floristry.R;
import com.esit.floristry.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderListRecycleViewAdapter extends RecyclerView.Adapter<OrderListRecycleViewAdapter.OrderListViewHolder> {

    private List<OrderItem> orderList = new ArrayList<>();
    String orderType;
    Context context;

    public OrderListRecycleViewAdapter(List<OrderItem> orderItems, String orderType, Context context) {
        this.orderList.addAll(orderItems);
        this.orderType = orderType;
        this.context = context;
    }


    @Override
    public OrderListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_list, parent, false);
        return new OrderListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderListViewHolder holder, int position) {
        holder.setGenreTitle(orderList.get(position));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }


    public class OrderListViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOrderItemOrderId;
        private TextView tvOrderItemOrderDate;
        private TextView tvOrderItemBusinessName;
        private TextView tvOrderItemOwnerName;
        private TextView tvOrderItemDeliveryDate;
        private TextView tvOrderItemOrderStatus;

        public OrderListViewHolder(View itemView) {
            super(itemView);

            tvOrderItemOrderId = (TextView) itemView.findViewById(R.id.tvOrderItemOrderId);
            tvOrderItemOrderDate = (TextView) itemView.findViewById(R.id.tvOrderItemOrderDate);
            tvOrderItemBusinessName = (TextView) itemView.findViewById(R.id.tvOrderItemBusinessName);

            tvOrderItemOwnerName = (TextView) itemView.findViewById(R.id.tvOrderItemOwnerName);
            tvOrderItemDeliveryDate = (TextView) itemView.findViewById(R.id.tvOrderItemDeliveryDate);
            tvOrderItemOrderStatus = (TextView) itemView.findViewById(R.id.tvOrderItemOrderStatus);

        }

        public void setGenreTitle(OrderItem orderItem) {
            tvOrderItemOrderId.setText("Order: #" + orderItem.getOrderId());
            tvOrderItemOrderDate.setText("Date: " + orderItem.getOrderDate());
            tvOrderItemBusinessName.setText(orderItem.getBusinessName());
            tvOrderItemOwnerName.setText(orderItem.getOwnerName());
            tvOrderItemDeliveryDate.setText("Delivery: " + orderItem.getDeliveryDate());

            if (orderType.equalsIgnoreCase("retailer_requested")) {
                tvOrderItemOrderStatus.setText("");
            } else if (orderType.equalsIgnoreCase("canceled_order")) {
                tvOrderItemOrderStatus.setTextColor(context.getResources().getColor(R.color.yellow_1));
                tvOrderItemOrderStatus.setText("Canceled: " + orderItem.getStatusInfo());
            } else if (orderType.equalsIgnoreCase("update_order")) {
                tvOrderItemOrderStatus.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                tvOrderItemOrderStatus.setText("Updated: " + orderItem.getStatusInfo());

            } else if (orderType.equalsIgnoreCase("confirmed_order")) {
                tvOrderItemOrderStatus.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                tvOrderItemOrderStatus.setText("Confirmed: " + orderItem.getStatusInfo());
            } else if (orderType.equalsIgnoreCase("shifted_order")) {
                tvOrderItemOrderStatus.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                tvOrderItemOrderStatus.setText("Shifted: " + orderItem.getStatusInfo());

            }
            else if (orderType.equalsIgnoreCase("received_order")) {
                tvOrderItemOrderStatus.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                tvOrderItemOrderStatus.setText("Received: " + orderItem.getStatusInfo());

            } else if (orderType.equalsIgnoreCase("submitted_order")) {
                tvOrderItemOrderStatus.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                tvOrderItemOrderStatus.setText("Submitted: " + orderItem.getStatusInfo());

            }
            else if (orderType.equalsIgnoreCase("Claimed_order")) {
                tvOrderItemOrderStatus.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));

                tvOrderItemOrderStatus.setText("Claimed: " + orderItem.getStatusInfo());

            }else {
                tvOrderItemOrderStatus.setText("");
            }

        }
    }
}




