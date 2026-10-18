package invalidTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import baseTest.BaseTest;
import org.example.pages.homeBage.HomePage;
import org.example.pages.productPage.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidCaseTest extends BaseTest {
    private static final Logger LOG = LogManager.getLogger(InvalidCaseTest.class);

    @Test
    public void search_Validation_with_Invalid_Input() {
        LOG.info("Starting search_Validation_with_Invalid_Input");
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);

        homePage.enterInSearchFieldText(jsonFileManager.getValue("product4").toString());
        homePage.clickOnSearchButton();
        productPage.getFirstProduct();
        productPage.getFirstCategoryText();
        productPage.backButton();
        productPage.getSecondProduct();
        productPage.getSecondCategoryText();
        LOG.info("Verifying expected test condition");
        Assert.assertNotEquals(productPage.returnFirstProduct(),productPage.returnSecondProduct());
    }

}
