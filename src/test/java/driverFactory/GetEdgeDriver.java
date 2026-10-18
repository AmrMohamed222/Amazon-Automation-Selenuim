package driverFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class GetEdgeDriver {
    private static final Logger LOG = LogManager.getLogger(GetEdgeDriver.class);

    private static WebDriver driver = null;

    public static WebDriver getEdgeDriver() {
        LOG.info("Starting getEdgeDriver");
        if (driver == null) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--incognito");
            driver = new EdgeDriver(edgeOptions);
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
