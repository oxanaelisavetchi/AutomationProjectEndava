package com.automation.project.actions;

import com.automation.project.entity.Product;
import com.automation.project.pages.ProductsPage;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.automation.project.drivers.DriverFactory.getDriver;

public class ProductActions {

    private final ProductsPage productsPage = new ProductsPage(getDriver());

    public void selectAZOption() {
        productsPage.selectProductSortContainer.click();
        productsPage.optionAZ.click();
    }

    public void selectZAOption() {
        productsPage.selectProductSortContainer.click();
        productsPage.optionZA.click();
    }

    public void selectLHOption() {
        productsPage.selectProductSortContainer.click();
        productsPage.optionLH.click();
    }

    public void selectHLOption() {
        productsPage.selectProductSortContainer.click();
        productsPage.optionHL.click();
    }

    public List<Product> getProducts() {

        List<Product> products = new ArrayList<>();

        for (int i = 0; i < productsPage.nameList.size(); i++) {
            Product product = new Product();
            product.setName(productsPage.nameList.get(i).getText());
            product.setDescription(productsPage.descriptionList.get(i).getText());
            product.setPrice(Double.parseDouble(productsPage.pricesList.get(i).getText().replace("$", "")));
            products.add(product);
        }

        return products;
    }

    public List<Product> getASCProductsByName(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getName)).toList();
    }

    public List<Product> getDESCProductsByName(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getName).reversed()).toList();
    }

    public List<Product> getASCProductsByPrice(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getPrice)).toList();
    }

    public List<Product> getDESCProductsByPrice(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).toList();
    }
}
