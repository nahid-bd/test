package com.esit.floristry.model;

public class Owner {

    String name;
    String businessName;
    String mobile;
    String district;
    String upzila;

    public Owner(String name, String businessName, String mobile, String district, String upzila, String address) {
        this.name = name;
        this.businessName = businessName;
        this.mobile = mobile;
        this.district = district;
        this.upzila = upzila;
        this.address = address;
    }

    public Owner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getUpzila() {
        return upzila;
    }

    public void setUpzila(String upzila) {
        this.upzila = upzila;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String address;
}
