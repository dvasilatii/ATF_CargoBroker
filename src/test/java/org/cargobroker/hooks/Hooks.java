package org.cargobroker.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.cargobroker.utils.PageUtils;
import org.cargobroker.utils.Utils;
import org.openqa.selenium.WebDriver;

public class Hooks {
    @Before("@UI")
    public void openBrowser() {
        WebDriver driver = PageUtils.getDriver();

        driver.get(Utils.getProperty("cargoURL"));
        driver.manage().window().maximize();
    }

    @After("@UI")
    public void closeBrowser() {
        PageUtils.getDriver().quit();
    }
}
