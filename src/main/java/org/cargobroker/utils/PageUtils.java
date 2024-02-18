package org.cargobroker.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;


public abstract class PageUtils {
    private static WebDriver driver;
    public static WebDriver getDriver() {
        if (driver == null) {
            String browserName = Utils.getProperty("browser");

            if (browserName.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }

            if (browserName.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
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
        }
        catch (Exception e) {
            e.getMessage(); // TODO: implement logger
        }
    }
    public void waitElementToDisappear(WebElement element) {
        try {
            int duration = Integer.parseInt(Utils.getProperty("waiterDuration"));
            WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(duration));
            w.until(ExpectedConditions.invisibilityOf(element));
        }
        catch (Exception e) {
            e.getMessage(); // TODO: implement logger
        }
    }
    public static void navigateTo(String section) {
        HashMap<String, String> sideMenu = Utils.parseJson(
    System.getProperty("user.dir") + "/src/test/resources/jsonData/sideMenuSections.json"
        );
        try {
            String url = sideMenu.get(section);
            driver.findElement(By.cssSelector("#presence-menu-side a[href='" + url + "']")).click();
        }
        catch (Exception e) {
            e.getMessage(); // TODO: implement logger
        }
    }
    public void selectItemFromDropdown(WebElement field, String value) {
        Select selectField = new Select(field);
        selectField.selectByVisibleText(value);
    }
    public void enterFieldValue(WebElement field, String value) {
        field.sendKeys(value);
    }
    public void clickOnElement(WebElement element) {
        element.click();
    }
}
