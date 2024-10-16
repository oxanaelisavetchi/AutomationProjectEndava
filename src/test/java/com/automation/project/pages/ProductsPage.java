package com.automation.project.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    @FindBy(css = "div.inventory_item_name")
    public List<WebElement> nameList;
    
    @FindBy(css = "div.inventory_item_desc")
    public List<WebElement> descriptionList;

    @FindBy(css = "div.inventory_item_price")
    public List<WebElement> pricesList;

    @FindBy(css = "select[data-test='product-sort-container']")
    public WebElement selectProductSortContainer;

    @FindBy(xpath = "//option[@value='az']")
    public WebElement optionAZ;

    @FindBy(xpath = "//option[@value='za']")
    public WebElement optionZA;

    @FindBy(xpath = "//option[@value='lohi']")
    public WebElement optionLH;

    @FindBy(xpath = "//option[@value='hilo']")
    public WebElement optionHL;

    @FindBy(css = "#shopping_cart_container")
    public WebElement shoppingCart;

    @Override
    public WebElement getAnchorElement() {
        return shoppingCartContainer;
    }

}

