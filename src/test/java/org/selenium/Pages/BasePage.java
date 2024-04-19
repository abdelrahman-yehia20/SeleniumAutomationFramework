package org.selenium.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.Utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {

    public WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait waitLong;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        waitLong = new WebDriverWait(driver,Duration.ofSeconds(15));
    }

    public void load(String endPoint){
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    public void waitForOverlayToDisappear(By overlay)
    {
        List<WebElement> overlays = driver.findElements(overlay);
        System.out.println("OVERLAY SIZE :" + overlays.size());
        if(overlays.size()>0) {
            new WebDriverWait(driver, Duration.ofSeconds(15)).
                    until(ExpectedConditions.invisibilityOfAllElements(overlays));
            System.out.println("Overlays are invisible: ");
        }
        else{
            System.out.println("OVERLAY NOT FOUND ");
        }

    }

    public WebElement waitForElementTOBeVisible(By element){
       return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
