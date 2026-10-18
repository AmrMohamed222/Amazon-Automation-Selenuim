package org.example.pages.homeBage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private static final Logger LOG = LogManager.getLogger(HomePage.class);


    public HomePage(WebDriver driver) {
        super(driver);
        LOG.info("Starting HomePage");
    }

    private final By searchField = By.id("twotabsearchtextbox");
    private final By searchButton = By.id("nav-search-submit-button");
    private final By result_text = By.xpath("//h2[contains(.,'Results')]");

    public void enterInSearchFieldText(String value) {
        LOG.info("Entering search term: {}", value);
        findElement(searchField).sendKeys(value);
    }

    public void clickOnSearchButton() {
        LOG.info("Starting clickOnSearchButton");
        findElement(searchButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(result_text));
    }

}
