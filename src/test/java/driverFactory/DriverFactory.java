package driverFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private static final Logger LOG = LogManager.getLogger(DriverFactory.class);

    public static WebDriver getWebDriver(String browser) {
        LOG.info("Starting getWebDriver");

        WebDriver driver;
        switch (browser.toLowerCase().trim()) {
            case "chrome":
                driver = GetChromeDriver.getChromeDriver();
                break;
            case "edge":
                driver = GetEdgeDriver.getEdgeDriver();
                break;
            case "firefox":
                driver = GetFirefoxDriver.getFirefoxDriver();
                break;
            default:
                LOG.error("Unsupported browser requested: {}", browser);
                throw new IllegalArgumentException("Invalid browser name: " + browser);
        }
        return driver;
    }

    public static void quitWebDriver(String browser) {
        LOG.info("Starting quitWebDriver");

        switch (browser.toLowerCase().trim()) {
            case "chrome":
                GetChromeDriver.quitDriver();
                break;
            case "edge":
                GetEdgeDriver.quitDriver();
                break;
            case "firefox":
                GetFirefoxDriver.quitDriver();
                break;
            default:
                LOG.error("Unsupported browser requested: {}", browser);
                throw new IllegalArgumentException("Invalid browser name: " + browser);
        }
    }
}
