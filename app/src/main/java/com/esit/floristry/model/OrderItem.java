package com.esit.floristry.model;

public class OrderItem {
    String orderId;
    String businessName;
    String ownerName;
    String orderDate;
    String deliveryDate;
    String statusInfo;

    public OrderItem() {
    }

    public OrderItem(String orderId, String businessName, String ownerName, String orderDate, String deliveryDate, String statusInfo) {
        this.orderId = orderId;
        this.businessName = businessName;
        this.ownerName = ownerName;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.statusInfo = statusInfo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }
}
