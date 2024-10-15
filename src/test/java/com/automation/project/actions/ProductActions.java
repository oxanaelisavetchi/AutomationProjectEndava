package com.automation.project.actions;

import com.automation.project.context.ScenarioContext;
import com.automation.project.entity.Product;
import com.automation.project.pages.ProductsPage;

import static com.automation.project.drivers.DriverFactory.getDriver;

public class ProductActions {
    private final ProductsPage productsPage = new ProductsPage(getDriver());
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    public void clickSortButton() {
        productsPage.selectProductSortContainer.click();
    }
    public void selectZAOption() {
        productsPage.optionZA.click();
    }
    public Product getProduct() {

        Product product = new Product();
        product.setName(productsPage.name.getText());
        product.setDescription(productsPage.description.getText());
//        product.setPrice(productsPage.price.getText());
        return product;
    }

}
