package com.automation.project.actions;

import com.automation.project.asserts.CustomAssert;
import com.automation.project.context.ScenarioContext;
import com.automation.project.drivers.DriverFactory;
import com.automation.project.pages.LoginPage;
import com.automation.project.pages.ProductsPage;
import com.automation.project.utilities.PageManager;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Method;

import static org.hamcrest.Matchers.is;


public class LoginActions extends DriverFactory {
    private final LoginPage loginPage = new LoginPage(getDriver());
    private final ProductsPage productsPage = new ProductsPage(getDriver());
    private final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    public void logIn(String username, String password) throws Throwable {
        enterLogInPage();
        enterCredentials(username, password);
        clickSubmit();
    }

    private void enterLogInPage() {
        scenarioContext.setCurrentPage(PageManager.getPageByName("Login"));
        CustomAssert.assertThat("User is on Login page", scenarioContext.getCurrentPage().getAnchorElement().isDisplayed(), is(true));
    }

    private void enterCredentials(String username, String password) {
        loginPage.getLoginField().sendKeys(username);
        loginPage.getPasswordField().sendKeys(password);
    }

    private void clickSubmit() throws Throwable {
        Method method = scenarioContext.getCurrentPage().getClass().getMethod("getSubmitButton");
        ((WebElement) method.invoke(scenarioContext.getCurrentPage())).click();
        scenarioContext.setCurrentPage(new ProductsPage(getDriver()));
        waitFor(2);
    }

    public void logOut() {
        productsPage.getBurgerMenuButton().click();
        waitFor(2);
        productsPage.getLogoutButton().click();
    }

    public String checkError() {
        return productsPage.getErrorMessage().getText();
    }
}