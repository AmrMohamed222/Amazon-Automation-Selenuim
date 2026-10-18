package org.example.pages.cartPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.example.pages.BasePage;
import org.example.pages.productPage.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private static final Logger LOG = LogManager.getLogger(CartPage.class);


    private final By addToCart = By.id("add-to-cart-button");
    private final By goToBasket = By.id("nav-cart");
    private final By quantityOnCart =By.id("nav-cart-count");
    private final By totalSubtotal  = By.xpath("//div[@data-name=\"Subtotals\"]//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']");
    private final By addQuantityOfProduct = By.xpath("//span[@class='a-icon a-icon-small-add']");
    private final By addOtherQuantityOfProduct =By.xpath("//button[@data-a-selector='increment' and contains(@tabindex,'0')]");
    private final By reduceQuantityOfProduct = By.xpath("//span[@class='a-icon a-icon-small-remove']");
    private final By reduceOtherQuantityOfProduct = By.xpath("//button[@data-action='a-stepper-decrement' and contains(@tabindex,'0')]");
    private final By quantityOfTheProduct = By.cssSelector("[data-a-selector='inner-value']");
    private final By cartTotalPrice = By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']");
    int totalPrice = 0;

    public CartPage(WebDriver driver) {
        super(driver);
        LOG.info("Starting CartPage");
    }


    public void clickAddToCartButton() {
        LOG.info("Starting clickAddToCartButton");
        findElement(addToCart).click();
    }
    public void GoToBasket() {
        LOG.info("Starting GoToBasket");
        findElement(goToBasket).click();
    }

    public boolean collectTotalPrice() {
        LOG.info("Starting collectTotalPrice");
        totalPrice = ProductPage.firstItemNumber + ProductPage.secondItemNumber;
        String temp = findElement(totalSubtotal).getText().replaceAll("[^0-9]", "");
        temp = temp.substring(0, temp.length() - 2);
        int subtotal = Integer.parseInt(temp);
        return subtotal == totalPrice;
    }

    public boolean totalPriceForSameProduct() {
        LOG.info("Starting totalPriceForSameProduct");
        totalPrice = ProductPage.firstItemNumber * 3;
        String temp = findElement(totalSubtotal).getText().replaceAll("[^0-9]", "");
        temp = temp.substring(0, temp.length() - 2);
        int subtotal = Integer.parseInt(temp);
        return subtotal == totalPrice;
    }

    public int checkQuantity() {
        LOG.info("Starting checkQuantity");
        return Integer.parseInt(findElement(quantityOnCart).getText());
    }

    public int checkQuantityOfProduct() {
        LOG.info("Starting checkQuantityOfProduct");
        return Integer.parseInt(findElement(quantityOfTheProduct).getText());
    }

    public  void clickAddQuantityOfProduct() {
        LOG.info("Starting clickAddQuantityOfProduct");
        findElement(addQuantityOfProduct).click();
        findElement(addOtherQuantityOfProduct).click();
    }

    public void clickReduceQuantityOfProduct() {
        LOG.info("Starting clickReduceQuantityOfProduct");
        findElement(reduceQuantityOfProduct).click();
        findElement(reduceOtherQuantityOfProduct).click();
    }
    public double getCartTotalPrice() {
        LOG.info("Starting getCartTotalPrice");
        String priceText = findElement(cartTotalPrice).getText();
        return Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
    }
}
