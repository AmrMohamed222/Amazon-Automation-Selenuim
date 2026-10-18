package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;

public class JSONFileManager {
    private static final Logger LOG = LogManager.getLogger(JSONFileManager.class);

    public LinkedHashMap<String,Object> data;
    public JSONFileManager(String filepath){
        LOG.info("Starting JSONFileManager");

        try{
            Type type = new TypeToken<LinkedHashMap<String,Object>>() {}.getType();
            data = new Gson().fromJson(new FileReader(filepath), type);
        }catch (Exception e){
            LOG.error("Failed to load JSON test data from: {}", filepath, e);
        }

    }
    public Object getValue(String key) {
        LOG.info("Starting getValue");
        return data.get(key);
    }
}
