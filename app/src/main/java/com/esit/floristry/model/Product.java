package com.esit.floristry.model;

public class Product {
    String id;
    String name;
    String grade;
    String unitPrice;
    String quantity;
    String totalCost;

    public Product() {
    }

    public Product(String id, String name, String grade, String unitPrice, String quantity, String totalCost) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalCost = totalCost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

}
