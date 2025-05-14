package com.automation.project.steps;

import com.automation.project.actions.ProductActions;
import com.automation.project.entity.Product;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class ProductSteps {
    private final ProductActions productActions = new ProductActions();

    @Then("user sorts products by name in {string} order")
    public void userSortsProductsByName(String order) {
        final String ASC = "asc";
        List<Product> expected;
        List<Product> actual;
        // "asc" -> variable
        if (order.equalsIgnoreCase(ASC)) {
            productActions.selectAZOption();
            actual = productActions.getProducts();
            expected = productActions.getASCProductsByName(actual);
            // try to move Assertions.assertEquals(expected, actual); out of if-else
//            Assertions.assertEquals(expected, actual);
        } else {
            productActions.selectZAOption();
            actual = productActions.getProducts();
            expected = productActions.getDESCProductsByName(actual);
        }
        Assertions.assertEquals(expected, actual);
    }

    @Then("user sorts products by price in {string} order")
    public void userSortsProductsByPrice(String order) {
        // try to use switch with default error "Unexpected file name: " {ads}
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
