package org.selenium.Tests;

import org.selenium.Pages.HomePage;
import org.selenium.Pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test
    public void navigateFromHomeToStoreUsingMainMenu(){

        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTitle(),"Store");
    }

}
