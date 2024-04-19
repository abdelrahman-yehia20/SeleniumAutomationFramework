package org.selenium.Tests;

import org.selenium.Objects.BillingAddress;
import org.selenium.Objects.User;
import org.selenium.Pages.CartPage;
import org.selenium.Pages.CheckOutPage;
import org.selenium.Pages.HomePage;
import org.selenium.Pages.StorePage;
import org.selenium.Objects.Product;
import org.selenium.Utils.ConfigLoader;
import org.selenium.Utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest{

    @Test
    public void  myFirstTestCase() throws IOException, InterruptedException {

        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class );
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu().
                search("Blue");
        Assert.assertEquals(storePage.getTitle(),"Search results: “" + searchFor + "”");
        storePage.clickOnAddToCartBtn(product.getName());
        Thread.sleep(5000);
        CartPage cartPage = storePage.clickOnViewCartLink();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckOutPage checkOutPage = cartPage.
                checkout().
                setBillingAddress(billingAddress).
               // selectBankTransfer().
                clickOnPlaceOrder();
        Thread.sleep(5000);
                Assert.assertEquals(checkOutPage.getSuccessOrderMessage(),"Thank you. Your order has been received.");
    }


    @Test
    public void  loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class );
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(),
                             ConfigLoader.getInstance().getPassword());

        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu().
                search(searchFor);
        Assert.assertEquals(storePage.getTitle(),"Search results: “" + searchFor + "”");
        storePage.clickOnAddToCartBtn(product.getName());
        Thread.sleep(5000);
        CartPage cartPage = storePage.clickOnViewCartLink();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckOutPage checkOutPage = cartPage.checkout();
        checkOutPage.clickOnLoginLink();
        Thread.sleep(3000);
        checkOutPage.
                  login(user).
                setBillingAddress(billingAddress).
               // selectBankTransfer().
                clickOnPlaceOrder();
        Thread.sleep(5000);
        Assert.assertEquals(checkOutPage.getSuccessOrderMessage(),"Thank you. Your order has been received.");

    }
}
