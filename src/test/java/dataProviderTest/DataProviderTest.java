package dataProviderTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.DataProvider;

public class DataProviderTest {
    private static final Logger LOG = LogManager.getLogger(DataProviderTest.class);

    @DataProvider(name = "credentials")
    public Object[][] getData() {
        LOG.info("Starting getData");
        return new Object[][] {
                {"Men's Watches", "3600"},
                {"Refrigerator", "6800"},
                {"Monitors", "5700"},
                {"Keyboards", "1800"},
        };

    };
}
