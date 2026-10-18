package productTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import baseTest.BaseTest;
import org.example.pages.cartPage.CartPage;
import org.example.pages.homeBage.HomePage;
import org.example.pages.productPage.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    private static final Logger LOG = LogManager.getLogger(ProductTest.class);


    @Test
    public void browseAndFilterProducts() {
        LOG.info("Starting browseAndFilterProducts");

        ProductPage productPage = new ProductPage(driver);
        HomePage homePage = new HomePage(driver);

        homePage.enterInSearchFieldText(jsonFileManager.getValue("product1").toString());
        homePage.clickOnSearchButton();
        productPage.clickOnFilterButton();
        productPage.setMaxPrice(Integer.parseInt(jsonFileManager.getValue("price5").toString()));
        productPage.sortLowToHighPrice();
        productPage.matchingSelectedFilters();
        productPage.checkSortingOrderCurrently();
    }
    
    @Test
    public void ProductDetailsVerificationTest(){
        LOG.info("Starting ProductDetailsVerificationTest");
        ProductPage productPage = new ProductPage(driver);
        HomePage homePage = new HomePage(driver);

        homePage.enterInSearchFieldText(jsonFileManager.getValue("product2").toString());
        homePage.clickOnSearchButton();
        productPage.checkProductInStock();
        LOG.info("Verifying product in stock");
        Assert.assertTrue(productPage.checkProductName());
        LOG.info("Verifying product name ");
        Assert.assertTrue(productPage.checkPrice());
        LOG.info("Verifying price");
        Assert.assertTrue(productPage.checkAvailabilityStatus());
        LOG.info("Verifying availability status");
        Assert.assertTrue(productPage.checkProductImages());
        LOG.info("Verifying product images");
        Assert.assertTrue(productPage.checkRatingSection());
        LOG.info("Verifying rating section");
        Assert.assertTrue(productPage.checkAddToCart());
    }



    @Test
    public void MultipleProductsWithFreeShippingWithinBudgetTest() {
        LOG.info("Starting MultipleProductsWithFreeShippingWithinBudgetTest");
        String[][] products = {
                {jsonFileManager.getValue("name1").toString(), jsonFileManager.getValue("price1").toString()},
                {jsonFileManager.getValue("name2").toString(), jsonFileManager.getValue("price2").toString()},
                {jsonFileManager.getValue("name3").toString(), jsonFileManager.getValue("price3").toString()},
                {jsonFileManager.getValue("name4").toString(), jsonFileManager.getValue("price4").toString()},
        };

        ProductPage productPage = new ProductPage(driver);
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);


        for (String[] product : products) {

            String productName = product[0];
            int maxPrice = Integer.parseInt(product[1]);

            homePage.enterInSearchFieldText(productName);
            homePage.clickOnSearchButton();

            productPage.clickFreeShippingButton();
            productPage.setMaxPrice(maxPrice);
            productPage.sortHighToLowPrice();
            productPage.matchingSelectedFilters();
            productPage.navigateFirstProduct();

            cartPage.clickAddToCartButton();
        }
        cartPage.GoToBasket();
        double totalPrice = cartPage.getCartTotalPrice();
        LOG.info("Verifying cart total price");
        Assert.assertTrue(totalPrice >= 17100 && totalPrice <= 18000, "Cart total should be between 17100 and 18000, but actual total is: " + totalPrice);
    }
}
