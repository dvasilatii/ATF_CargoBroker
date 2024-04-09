package org.cargobroker.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;

@Log4j2
public class Utils {
    private static Properties properties;

    public static String getProperty(String property) {
        if (properties == null) {
            try {
                FileInputStream fis = new FileInputStream("./src/main/resources/config.properties");
                properties = new Properties();
                properties.load(fis);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }

        return properties.getProperty(property);
    }

    public static <T> T parseJson(String filePath) {
        try {
            String jsonContent = FileUtils.readFileToString(new File(filePath));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonContent, new TypeReference<T>() {
            });
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static double getRandomDouble(double min, double max) {
        Random r = new Random();
        //TODO:remove higher

        return min + r.nextDouble() * (max - min);
    }

    public static String getCurrentDate() {
        return DateTimeFormatter.ofPattern(Utils.getProperty("screenshotDateFormat")).format(LocalDateTime.now());
    }
}