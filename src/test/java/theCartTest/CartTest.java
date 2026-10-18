package theCartTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import baseTest.BaseTest;
import org.example.pages.cartPage.CartPage;
import org.example.pages.homeBage.HomePage;
import org.example.pages.productPage.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    private static final Logger LOG = LogManager.getLogger(CartTest.class);

    @Test
    public void addMultipleProductsToCart() {
        LOG.info("Starting addMultipleProductsToCart");
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.enterInSearchFieldText(jsonFileManager.getValue("product3").toString());
        homePage.clickOnSearchButton();
        productPage.navigateFirstProduct();
        cartPage.clickAddToCartButton();
        productPage.doubleBackButton();
        productPage.navigateSecondProduct();
        cartPage.clickAddToCartButton();
        cartPage.GoToBasket();

        LOG.info("Verifying basket");

        Assert.assertTrue(cartPage.collectTotalPrice());
        LOG.info("Verifying collect total price");
        Assert.assertEquals(cartPage.checkQuantity(), 2);
    }

    @Test
    public void updateCartQuantityTest(){
        LOG.info("Starting updateCartQuantityTest");
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.enterInSearchFieldText(jsonFileManager.getValue("product3").toString());
        homePage.clickOnSearchButton();
        productPage.navigateFirstProduct();
        cartPage.clickAddToCartButton();
        cartPage.GoToBasket();
        cartPage.clickAddQuantityOfProduct();
        LOG.info("Verifying expected test condition");
        Assert.assertEquals(cartPage.checkQuantityOfProduct(), 3);
        LOG.info("Verifying expected test condition");
        Assert.assertTrue(cartPage.totalPriceForSameProduct());
        cartPage.clickReduceQuantityOfProduct();
        LOG.info("Verifying expected test condition");
        Assert.assertEquals(cartPage.checkQuantityOfProduct(), 1);
    }
}
