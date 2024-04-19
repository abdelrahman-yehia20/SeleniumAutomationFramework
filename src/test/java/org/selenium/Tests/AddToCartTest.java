package org.selenium.Tests;

import org.selenium.Objects.Product;
import org.selenium.Pages.CartPage;
import org.selenium.Pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {
    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).
                load().
                clickOnAddToCartBtn(product.getName())
                .clickOnViewCartLink();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }
}
