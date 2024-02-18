package org.cargobroker.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    private static Properties properties;
    public static String getProperty (String property) {
        if (properties == null) {
            try {
                FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                        + "//src//main//resources//config.properties");
                properties = new Properties();
                properties.load(fis);
            } catch (IOException e) {
                System.out.println(e.getMessage()); // TODO: implement logger
            }
        }

        return properties.getProperty(property);
    }
    public static <T> T parseJson(String filePath) {
        try {
            String jsonContent = FileUtils.readFileToString(new File(filePath));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonContent, new TypeReference<T>() {});
        }
        catch(Exception e) {
            System.out.println(e.getMessage()); // TODO: implement logger
            return null;
        }
    }
}
