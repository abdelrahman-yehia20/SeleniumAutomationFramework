package org.selenium.Tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.Pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class StoreTest extends BaseTest {

    @Test
    public void searchPartialTest(){

        String searchFor = "Blue";
        StorePage storePage = new StorePage(getDriver()).
                load()
        .search(searchFor);
        Assert.assertEquals(storePage.getTitle(),"Search results: “" + searchFor + "”");

    }
}
