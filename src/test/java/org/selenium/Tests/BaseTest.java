package org.selenium.Tests;

import org.selenium.Factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected ThreadLocal <WebDriver> driver = new ThreadLocal<>();



//    public void setDriver(WebDriver driver) {
//        this.driver.set(driver);
//    }

    public WebDriver getDriver(){
        return this.driver.get();
    }
    @Parameters("browser")
    @BeforeMethod
    public void OpenBrowser(@Optional("CHROME") String browser){
        //if(browser == null) browser = "CHROME";
        //@Optional("CHROME")
        browser = System.getProperty("browser",browser);
       this.driver.set(new DriverManager().initializeDriver( browser));
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " + "Driver = " + getDriver());

    }

    @AfterMethod
    public void closeDriver() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " + "Driver = " + getDriver());
        getDriver().quit();
    }
}
