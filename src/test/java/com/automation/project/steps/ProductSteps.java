package com.automation.project.steps;

import com.automation.project.actions.ProductActions;
import com.automation.project.entity.Product;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class ProductSteps {
    private final ProductActions productActions = new ProductActions();

    @Then("user sorts products by name in {string} order")
    public void userSortsProductsByName(String order) {
        if (order.equalsIgnoreCase("asc")) {
            productActions.selectAZOption();
            List<Product> actual = productActions.getProducts();
            List<Product> expected = productActions.getASCProductsByName(actual);
            Assertions.assertEquals(expected, actual);
        } else {
            productActions.selectZAOption();
            List<Product> actual = productActions.getProducts();
            List<Product> expected = productActions.getDESCProductsByName(actual);
            Assertions.assertEquals(expected, actual);
        }
    }

    @Then("user sorts products by price in {string} order")
    public void userSortsProductsByPrice(String order) {
        if (order.equalsIgnoreCase("asc")) {
            productActions.selectLHOption();
            List<Product> actual = productActions.getProducts();
            List<Product> expected = productActions.getASCProductsByPrice(actual);
            Assertions.assertEquals(expected, actual);
        } else {
            productActions.selectHLOption();
            List<Product> actual = productActions.getProducts();
            List<Product> expected = productActions.getDESCProductsByPrice(actual);
            Assertions.assertEquals(expected, actual);
        }
    }

}
