package org.selenium.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StorePage extends BasePage{

   private By searchFld = By.id("woocommerce-product-search-field-0");
   private By searchBtn = By.cssSelector("button[value=\"Search\"]");
   private By title = By.cssSelector("h1[class=\"woocommerce-products-header__title page-title\"]");
   /*private By addToCartBtn = */
    private By viewCartLink = By.cssSelector("a[title=\"View cart\"]");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public Boolean isLoaded(){
        return wait.until(ExpectedConditions.urlContains("/store"));
    }

    public StorePage enterTextToSearchFld(String txt){
        driver.findElement(searchFld).sendKeys(txt);
        return this;
    }

    public StorePage load(){
        load("/store");
        return this;
    }

    public StorePage clickOnSearchBtn(){
        driver.findElement(searchBtn).click();
        return this;
    }

    public StorePage search(String txt){
        enterTextToSearchFld(txt).clickOnSearchBtn();
        wait.until(ExpectedConditions.urlContains(txt));
        return this;
    }

    public String getTitle(){
        return driver.findElement(title).getText();
    }

    public By getAddToCartBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public StorePage clickOnAddToCartBtn(String productName){
        driver.findElement(getAddToCartBtnElement(productName)).click();
        waitForElementTOBeVisible(viewCartLink);
        return this;
    }

    public CartPage clickOnViewCartLink(){
        driver.findElement(viewCartLink).click();
        return new CartPage(driver);
    }


}
