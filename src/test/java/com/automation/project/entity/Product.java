package com.automation.project.entity;

public class Product {

    private String name;
    private String description;
    private double price;

    public Product() {

    }

    public Product(String name, String description, double price) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
