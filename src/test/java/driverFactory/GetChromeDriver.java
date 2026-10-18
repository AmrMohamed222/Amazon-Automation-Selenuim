package driverFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GetChromeDriver {
    private static final Logger LOG = LogManager.getLogger(GetChromeDriver.class);

    public static WebDriver driver = null;

    public static WebDriver getChromeDriver() {
        LOG.info("Starting getChromeDriver");
        if (driver == null) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--incognito");
            driver = new ChromeDriver(chromeOptions);
        }
        return driver;
    }

    public static void quitDriver() {
        LOG.info("Starting quitDriver");
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
