package org.selenium.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.Objects.BillingAddress;
import org.selenium.Objects.User;

import java.time.Duration;
import java.util.List;

public class CheckOutPage extends BasePage {

    private By firstNameFld = By.id("billing_first_name");
    private By lastNameFld = By.id("billing_last_name");
    private By addressLineOneFld = By.id("billing_address_1");
    private By cityFld = By.id("billing_city");
    private By postalCodeFld = By.id("billing_postcode");
    private By email = By.id("billing_email");
    private By placeOrderBtn = By.id("place_order");
    private By successMessage = By.cssSelector(".woocommerce-notice");
    private By clickHereToLoginLink = By.className("showlogin");
    private By usernameFld = By.id("username");
    private By passwordFld = By.id("password");
    private By loginBtn = By.name("login");
    private By overlay = By.cssSelector(".blockUI.blockOverlay");

    private By countryDropDown = By.id("billing_country");
    private By alternateCountryDropDown= By.id("select2-billing_country-container");
    private By alternateStateDropDown = By.id("select2-billing_state-container");
    private By stateDropDown = By.id("billing_state");
    private By directBankTransferRadioBtn = By.id("payment_method_basc");
    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public CheckOutPage clickOnLoginLink(){
        driver.findElement(clickHereToLoginLink).click();
        return this;
    }

    public CheckOutPage enterUserName(String userName){
        driver.findElement(usernameFld).clear();
        driver.findElement(usernameFld).sendKeys(userName);
        return this;
    }

    public CheckOutPage enterPassword(String password){
        driver.findElement(passwordFld).clear();
        driver.findElement(passwordFld).sendKeys(password);
        return this;
    }

    public CheckOutPage clickOnLoginBtn(){
        driver.findElement(loginBtn).click();
        return this;
    }
    public CheckOutPage login(User user){
        enterUserName(user.getUserName()).enterPassword(user.getPassword()).clickOnLoginBtn();
        return this;
       //return enterUserName(userName).enterPassword(password).clickOnLoginBtn();
    }


    public CheckOutPage enterFirstName(String name){
        WebElement e =  waitForElementTOBeVisible(firstNameFld);
        e.clear();
       e.sendKeys(name);
       // for sendKeys we don't need to call until method for sendKeys due to there is no refreshment or api calling

        //driver.findElement(firstNameFld).clear();
        //driver.findElement(firstNameFld).sendKeys(name);
        return this;
    }
    public CheckOutPage enterLastName(String name){
        driver.findElement(lastNameFld).clear();
        driver.findElement(lastNameFld).sendKeys(name);
        return this;
    }

    public CheckOutPage selectCountry(String country){
       // Select select = new Select(driver.findElement(countryDropDown));
       // select.selectByVisibleText(country);

        // this code is using due to i can run on firefox browser .
        wait.until(ExpectedConditions.elementToBeClickable(alternateCountryDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + country + "']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }
    public CheckOutPage enterAddressOne(String address){
        driver.findElement(addressLineOneFld).clear();
        driver.findElement(addressLineOneFld).sendKeys(address);
        return this;
    }

    public CheckOutPage enterCity(String city){
        driver.findElement(cityFld).clear();
        driver.findElement(cityFld).sendKeys(city);
        return this;
    }

    public CheckOutPage selectState(String state){
       // Select select = new Select(driver.findElement(stateDropDown));
       // select.selectByVisibleText(state);

        // this code is using due to i can run on firefox browser .
        wait.until(ExpectedConditions.elementToBeClickable(alternateStateDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + state + "']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }

    public CheckOutPage enterPostalCode(String code){
        driver.findElement(postalCodeFld).clear();
        driver.findElement(postalCodeFld).sendKeys(code);
        return this;
    }
    public CheckOutPage enterEmail(String email){
        driver.findElement(this.email).clear();
        driver.findElement(this.email).sendKeys(email);
        return this;
    }

    public CheckOutPage setBillingAddress(BillingAddress billingAddress){
        enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterAddressOne(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterPostalCode(billingAddress.getPostalCode()).
                enterEmail(billingAddress.getEmail());
        return this;
    }

    public CheckOutPage clickOnPlaceOrder(){
        waitForOverlayToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public CheckOutPage selectBankTransfer(){
       // WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        WebElement e = driver.findElement(directBankTransferRadioBtn);
        if(!e.isSelected()){
            e.click();
        }
        return this;
    }
    public String getSuccessOrderMessage(){
        return driver.findElement(successMessage).getText();
    }

}
