package org.selenium.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends  BasePage{

    By productName = By.cssSelector("td[class=\"product-name\"] a");
    By checkOutBtn = By.cssSelector("a[class=\"checkout-button button alt wc-forward\"]");
    By cartHeading = By.cssSelector("h1.has-text-align-center");
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isLoaded(){
        return wait.until(ExpectedConditions.textToBe(cartHeading,"Cart"));
    }

    public String getProductName(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
       // return driver.findElement(productName).getText();
    }

    public CheckOutPage checkout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkOutBtn)).click();
        //driver.findElement(checkOutBtn).click();
        return new CheckOutPage(driver);
    }

}
