package org.example.pages.productPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.example.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductPage extends BasePage {
    private static final Logger LOG = LogManager.getLogger(ProductPage.class);


    public ProductPage(WebDriver driver) {
        super(driver);
        LOG.info("Starting ProductPage");
    }


    private final By brandFilter = By.cssSelector("[aria-label='Apply the filter ASUS to narrow results']");
    private final By maxPriceSlider  = By.xpath("//input[@id='p_36/range-slider_slider-item_upper-bound-slider']");
    private final By targetMax = By.xpath("//label[@for='p_36/range-slider_slider-item_upper-bound-slider']//span");
    private final By result_text = By.xpath("//h2[contains(.,'Results')]");
    private final By sortList = By.className("a-dropdown-container");
    private final By sortLowToHighPrice = By.xpath("//a[@class='a-dropdown-link' and contains (.,'Low to')]");
    private final By sortHighTOLowPrice = By.xpath("//a[@class='a-dropdown-link' and contains (.,'High to')]");
    private final By selectFirstProduct = By.xpath("//div[@role='listitem'][1]");
    private final By selectSecondProduct = By.xpath("//div[@role='listitem'][2]");
    private final By itemPrice = By.xpath("//div[@id='corePriceDisplay_desktop_feature_div']//span[@class='a-price-whole']");
    private final By numOfItem = By.xpath("//span[contains(text(),' results for')]");
    private final By priceForAllProduct = By.className("a-price-whole");
    private final By titleRecipe = By.xpath("//a[@class='a-link-normal s-line-clamp-3 s-link-style a-text-normal']//span");
    private final By firstCategory = By.xpath("//ul[@class='a-unordered-list a-horizontal a-size-small']//span[@class='a-list-item']");
    private final By productName = By.xpath("//span[@class='a-size-large product-title-word-break']");
    private final By price = By.xpath("//div[@id='corePriceDisplay_desktop_feature_div']//span[@class='a-price-whole']");
    private final By availabilityStatus = By.xpath("//span[@class='a-size-medium a-color-success primary-availability-message']");
    private final By productImages = By.xpath("//div[@id='imgTagWrapperId']");
    private final By ratingSection = By.xpath("//div[@id='averageCustomerReviews_feature_div']");
    private final By addToCart = By.xpath("//input[@id='add-to-cart-button']");
    private final By selectProductInStock = By.xpath("//div[@data-component-type='s-search-result' and not(.//span[contains(text(),'left in stock')])]");
    private final By freeShippingButton = By.xpath("//li[@id='p_n_free_shipping_eligible/21909080031']//div[@class='a-checkbox a-checkbox-fancy s-navigation-checkbox aok-float-left']");
    public static int firstItemNumber = 0;
    public static int secondItemNumber = 0;
    String firstCategoryText;
    String secondCategoryText;

    public void clickOnFilterButton() {
        LOG.info("Starting clickOnFilterButton");
        findElement(brandFilter).click();
    }

    public void sortLowToHighPrice() {
        LOG.info("Sorting products by price: low to high");
        findElement(sortList).click();
        findElement(sortLowToHighPrice).click();
        explicitWaitElementLocated(result_text);
    }

    public void sortHighToLowPrice() {
        LOG.info("Sorting products by price: high to low");
        findElement(sortList).click();
        findElement(sortHighTOLowPrice).click();
        explicitWaitElementLocated(result_text);
    }

    public void setMaxPrice(int targetMaxPrice) {
        LOG.info("Setting maximum product price to: {}", targetMaxPrice);
        WebElement maxSlider = findElement(maxPriceSlider);
        String temp = maxSlider.getAttribute("aria-valuetext");
        assert temp != null;
        int currentPrice = Integer.parseInt(temp.replaceAll("[^0-9]", ""));
        int steps = (currentPrice - targetMaxPrice) / 100;
        Actions actions = new Actions(driver);
        actions.moveToElement(maxSlider).click().perform();
        maxSlider.sendKeys(Keys.END);

        for (int i = 0; i < steps; i++) {
            int tempPrice = Integer.parseInt(findElement(targetMax).getText().replaceAll("[^0-9]", ""));
            if (tempPrice == targetMaxPrice) {
                break;
            }
            maxSlider.sendKeys(Keys.ARROW_LEFT);
        }
        String firstNumberOfItem = findElement(numOfItem).getText().split(" results")[0];
        By afterSlider = By.xpath( "//span[contains(text(),'"
                + firstNumberOfItem +
                " results for')]");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(afterSlider));
    }

    public void getFirstProduct() {
        LOG.info("Starting getFirstProduct");
        findElement(selectFirstProduct).click();
    }

    public void getSecondProduct() {
        LOG.info("Starting getSecondProduct");
        findElement(selectSecondProduct).click();
    }

    public void navigateFirstProduct() {
        LOG.info("Starting navigateFirstProduct");
        findElement(selectFirstProduct).click();
        firstItemNumber = Integer.parseInt(findElement(itemPrice).getText().replace(",",""));
    }

    public void navigateSecondProduct() {
        LOG.info("Starting navigateSecondProduct");
        findElement(selectSecondProduct).click();
        secondItemNumber = Integer.parseInt(findElement(itemPrice).getText().replace(",",""));
    }

    public void backButton(){
        LOG.info("Starting backButton");
        backNavigation();
    }

    public void doubleBackButton(){
        LOG.info("Starting doubleBackButton");
        backNavigation();
        backNavigation();
    }

    public void matchingSelectedFilters() {
        LOG.info("Starting matchingSelectedFilters");
        List<WebElement> allTitle = findElements(titleRecipe);
        int listSize = allTitle.size();
        for (int i = 0; i < listSize - 1; i++) {
            if(!(allTitle.get(i).getText().contains("Asus")||allTitle.get(i).getText().contains("ASUS")))
            {
                LOG.warn("Product does not match ASUS filter: {}", allTitle.get(i).getText());
            }
        }
    }

    public void checkSortingOrderCurrently() {
        LOG.info("Starting checkSortingOrderCurrently");
        List<WebElement> allPrice = findElements(priceForAllProduct);
        int listSize = allPrice.size();

        for (int i = 0; i < listSize - 1; i++) {
            int currentProductPrice = Integer.parseInt(allPrice.get(i).getText().replace(",", ""));
            int nextProductPrice = Integer.parseInt(allPrice.get(i + 1).getText().replace(",", ""));
            if (!(currentProductPrice <= nextProductPrice)) {
                LOG.error("Sorting validation failed: current product price {} is greater than next product price {}", currentProductPrice, nextProductPrice);
                throw new RuntimeException("Current product price > next product price");
            }
        }
    }

    public void getFirstCategoryText() {
        LOG.info("Starting getFirstCategoryText");
        firstCategoryText = findElements(firstCategory).getFirst().getText();
    }

    public void getSecondCategoryText()  {
        LOG.info("Starting getSecondCategoryText");
        secondCategoryText = findElements(firstCategory).getFirst().getText();
    }

    public String returnFirstProduct() {
        LOG.info("Starting returnFirstProduct");
       return firstCategoryText;
    }

    public String returnSecondProduct() {
        LOG.info("Starting returnSecondProduct");
        return secondCategoryText;
    }

    public boolean checkProductName() {
        LOG.info("Starting checkProductName");
        return findElement(productName).isDisplayed();
    }

    public boolean checkPrice() {
        LOG.info("Starting checkPrice");
        return findElement(price).isDisplayed();
    }

    public boolean checkAvailabilityStatus() {
        LOG.info("Starting checkAvailabilityStatus");
        return findElement(availabilityStatus).isDisplayed();
    }

    public boolean checkProductImages() {
        LOG.info("Starting checkProductImages");
        return findElement(productImages).isDisplayed();
    }

    public boolean checkRatingSection() {
        LOG.info("Starting checkRatingSection");
        return findElement(ratingSection).isDisplayed();
    }

    public boolean checkAddToCart() {
        LOG.info("Starting checkAddToCart");
        return findElement(addToCart).isDisplayed();
    }

    public void checkProductInStock(){
        LOG.info("Starting checkProductInStock");
        findElements(selectProductInStock).getFirst().click();
    }

    public void clickFreeShippingButton(){
        LOG.info("Starting clickFreeShippingButton");
        findElement(freeShippingButton).click();
    }
}
