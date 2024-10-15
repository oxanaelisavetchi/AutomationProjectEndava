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

    @FindBy(xpath = "//div[@data-test='inventory-item-name']")
    public WebElement name;

    @FindBy(xpath = "//div[@data-test='inventory-item-desc']")
    public WebElement description;

    @FindBy(xpath = "//div[@class='inventory_details_price']")
    public WebElement price;

    @FindBy(xpath = "//*[@id='add-to-cart']")
    public WebElement buttonAddCart;

    @FindBy(xpath = "//select")
    public WebElement selectProductSortContainer;

    @FindBy(xpath = "//option[@value='az']")
    public WebElement optionAZ;

    @FindBy(xpath = "//option[@value='za']")
    public WebElement optionZA;

    @FindBy(xpath = "//option[@value='lohi']")
    public WebElement optionLH;

    @FindBy(xpath = "//option[@value='hilo']")
    public WebElement optionHL;


    @Override
    public WebElement getAnchorElement() {
        return shoppingCartContainer;
    }

}

