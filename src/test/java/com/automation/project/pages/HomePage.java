package com.automation.project.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='login_logo']")
    private WebElement pageTitleElement;

    @Override
    public WebElement getAnchorElement() {
        return getPageTitleElement();
    }
}
