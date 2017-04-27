package com.example.jainsaab.inventoryapp;

public class Inventory {

    private String mProductName;
    private int mPrice;
    private int mQuantity;
    private String mImage;
    private String mSupplier;

    public Inventory() {
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String product) {
        mProductName = product;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getSupplier() {
        return mSupplier;
    }

    public void setSupplier(String supplier) {
        mSupplier = supplier;
    }
}
