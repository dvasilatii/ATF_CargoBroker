package org.cargobroker.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.cargobroker.context.DataKeys;
import org.cargobroker.context.ScenarioContext;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Log4j2
public abstract class PageUtils {
    private static WebDriver driver;
    private static final ScenarioContext CONTEXT = ScenarioContext.getScenarioInstance();

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserName = Utils.getProperty("browser");
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless"));

            if (browserName.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();

                if (isHeadless) {
                    options.addArguments("headless");
                }

                driver = new ChromeDriver(options);
            }

            if (browserName.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();

                if (isHeadless) {
                    options.addArguments("headless");
                }

                driver = new EdgeDriver(options);
            }
        }

        return driver;
    }

    public void initWebElements() {
        PageFactory.initElements(driver, this);
    }

    public void waitWebElement(WebElement findBy) {
        try {
            int duration = Integer.parseInt(Utils.getProperty("waiterDuration"));
            WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(duration));
            w.until(ExpectedConditions.visibilityOf(findBy));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void waitElementToDisappear(WebElement element) {
        try {
            int duration = Integer.parseInt(Utils.getProperty("waiterDuration"));
            WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(duration));
            w.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public static void navigateTo(String section) {
        HashMap<String, String> sideMenu = Utils.parseJson(Utils.getProperty("pathToMenuSections"));

        try {
            String url = sideMenu.get(section);
            driver.findElement(By.cssSelector("#presence-menu-side a[href='" + url + "']")).click();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        log.info("user is redirected to " + section + " section");
        PageUtils.takeScreenshot("userNavigatedTo_" + section);
    }

    public static void takeScreenshot(String id) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String date = DateTimeFormatter.ofPattern(Utils.getProperty("screenshotDateFormat")).format(LocalDateTime.now());
        String location = Utils.getProperty("pathToEvidence")
                + "/" + CONTEXT.getData(DataKeys.TEST_RUN_TIME)
                + "/" + CONTEXT.getData(DataKeys.TEST_CURRENT)
                + "/" + date + "_" + id + Utils.getProperty("screenshotExtension");

        try {
            FileUtils.copyFile(screenshot, new File(location));
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    public void selectItemFromDropdown(WebElement field, String value) {
        if (field == null) {
            log.error("can't select '" + value + "' from dropdown");
            return;
        }

        Select selectField = new Select(field);
        selectField.selectByVisibleText(value);
    }

    public void enterFieldValue(WebElement field, String value) {
        if (field == null) {
            log.error("field can't be filled with '" + value + "'");
            return;
        }

        field.sendKeys(value);
    }

    public void clickOnElement(WebElement element) {
        if (element == null) {
            log.error("element can't be clicked");
            return;
        }

        element.click();
    }

    public static void quit() {
        ScenarioContext.getScenarioInstance().resetPages();

        driver.quit();
        driver = null;
    }
}