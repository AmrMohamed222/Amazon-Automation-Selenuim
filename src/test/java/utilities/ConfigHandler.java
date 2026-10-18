package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigHandler {
    private static final Logger LOG = LogManager.getLogger(ConfigHandler.class);

    Properties properties;
    public ConfigHandler(String filePath) {
        LOG.info("Starting ConfigHandler");
        properties = new Properties();
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);
        }catch(Exception e){
            LOG.error("Failed to load configuration file: {}", filePath, e);
        }
    }
    public String getValue(String key) {
        LOG.info("Starting getValue");
        return properties.getProperty(key);
    }
}
