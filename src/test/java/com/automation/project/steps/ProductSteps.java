package com.automation.project.steps;

import com.automation.project.actions.LoginActions;
import com.automation.project.actions.ProductActions;
import com.automation.project.asserts.CustomAssert;
import com.automation.project.context.ScenarioContext;
import com.automation.project.entity.Product;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.Matchers.is;

public class ProductSteps {
    private final LoginActions logInActions = new LoginActions();
    private final ProductActions productActions = new ProductActions();
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    @When("users enters the {string} and {string}")
    public void usersLogsWithCredentials(String userName, String password) throws Throwable {
        logInActions.logIn(userName, password);
    }

    @Then("users enters on product page")
    public void usersEntersOnProductsPage() {
        CustomAssert.assertThat("User in on Account page  ", scenarioContext.getCurrentPage().getAnchorElement().isDisplayed(), is(true));
    }

    @And("users sort product by name")
    public void usersSortProductByName() {
        Product product = productActions.getProduct();
        productActions.clickSortButton();
        productActions.selectZAOption();
        Product sortedProduct = productActions.getProduct();

        product.equals(sortedProduct);
    }

    @Then("users enters on product page")
    public void usersEntersOnProductPage() {
        CustomAssert.assertThat("User in on Account page  ", scenarioContext.getCurrentPage().getAnchorElement().isDisplayed(), is(true));
    }


}
