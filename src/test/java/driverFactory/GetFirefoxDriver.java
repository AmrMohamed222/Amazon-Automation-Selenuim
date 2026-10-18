package driverFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GetFirefoxDriver {
    private static final Logger LOG = LogManager.getLogger(GetFirefoxDriver.class);

    public static WebDriver driver = null;

    public static WebDriver getFirefoxDriver() {
        LOG.info("Starting getFirefoxDriver");
        if (driver == null) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--incognito");
            driver = new FirefoxDriver(firefoxOptions);
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
