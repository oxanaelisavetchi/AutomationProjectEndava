package com.automation.project.pages;


import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='shopping_cart_container']")
    public WebElement shoppingCartContainer;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuButton;

    @FindBy(linkText = "Logout")
    private WebElement logoutButton;

    @FindBy(css = ".error-message-container")
    private WebElement errorMessage;

    @Override
    public WebElement getAnchorElement() {
        return shoppingCartContainer;
    }

}

