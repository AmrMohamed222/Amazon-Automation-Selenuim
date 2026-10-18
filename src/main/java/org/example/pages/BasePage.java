package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    private static final Logger LOG = LogManager.getLogger(BasePage.class);

    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        LOG.info("Starting BasePage");
        this.driver = driver;
    }

    public WebElement findElement(By locator) {
        LOG.info("Starting findElement");
        LOG.debug("Waiting for element located by: {}", locator);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator)
    {
        LOG.info("Starting findElements");
        LOG.debug("Waiting for element located by: {}", locator);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }

    public void backNavigation(){
        LOG.info("Starting backNavigation");
        driver.navigate().back();
    }

    public void explicitWaitElementLocated(By locator)
    {
        LOG.info("Starting explicitWaitElementLocated");
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

