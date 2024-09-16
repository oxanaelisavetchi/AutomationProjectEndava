package com.automation.project.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public abstract class BasePage {

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private final String URL = "";

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    public WebElement linkShoppingCart;

    public WebElement getAnchorElement() {
        return linkShoppingCart;
    }
}