package com.automation.project.steps;

import com.automation.project.actions.ProductActions;
import com.automation.project.entity.Product;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class ProductSteps {
    private final ProductActions productActions = new ProductActions();

    @And("user sort asc product by name")
    public void usersSortASCProductByName() {
        productActions.selectAZOption();
        List<Product> productList = productActions.getProducts();
        Assertions.assertEquals(productList, productActions.getASCProductsByName(productList));
    }

    @And("user sort desc product by name")
    public void usersSortDESCProductByName() {
        productActions.selectZAOption();
        List<Product> productList = productActions.getProducts();
        Assertions.assertEquals(productList, productActions.getDESCProductsByName(productList));
    }

    @And("user sort asc product by price")
    public void usersSortASCProductByPrice() {
        productActions.selectLHOption();
        List<Product> productList = productActions.getProducts();
        Assertions.assertEquals(productList, productActions.getASCProductsByPrice(productList));
    }

    @And("user sort desc product by price")
    public void usersSortDESCProductByPrice() {
        productActions.selectHLOption();
        List<Product> productList = productActions.getProducts();
        Assertions.assertEquals(productList, productActions.getDESCProductsByPrice(productList));
    }

}
