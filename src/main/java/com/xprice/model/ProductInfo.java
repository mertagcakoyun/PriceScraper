package com.xprice.model;

/**
 * Model class representing product information including website, price, and title.
 */
public class ProductInfo {
    private String website;
    private double price;

    private String title;

    public ProductInfo(String website, double price, String title) {
        this.website = website;
        this.price = price;
        this.title = title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
