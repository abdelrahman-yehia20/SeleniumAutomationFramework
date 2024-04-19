package org.selenium.Factory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.constants.DriverType;

public class DriverManager {
    public WebDriver initializeDriver(String browser){
        WebDriver driver;
        String localBrowser;
       //browser = System.getProperty("browser",browser); // this line if you want to use mvn or jvm, i moved this line to basetestclass
       // localBrowser = browser; // this line if you want to use testng.xml file
        switch (DriverType.valueOf(browser)) {
            case CHROME -> {
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case FIREFOX -> {
                io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            default -> throw new IllegalStateException("Invalid browser name :  " + browser);
        }



//        io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
//        WebDriver driver = new FirefoxDriver();

        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }

    /*  the old function of initialized driver
    public WebDriver initializeDriver1(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
    */

}
