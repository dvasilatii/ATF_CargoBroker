package org.cargobroker.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.cargobroker.utils.DBUtils;
import org.cargobroker.utils.PageUtils;
import org.cargobroker.utils.Utils;
import org.openqa.selenium.WebDriver;

public class Hooks {
    @Before("@UI")
    public void onTestStart() {
        WebDriver driver = PageUtils.getDriver();

        driver.get(Utils.getProperty("cargoURL"));
        driver.manage().window().maximize();
    }

    @After("@UI")
    public void onTestFinish(Scenario scenario) {
        if (scenario.isFailed()) {
            PageUtils.takeScreenshot("failedTest_" + scenario.getName().replace(' ', '_'));
        }

        PageUtils.getDriver().quit();
    }

    @Before("@DB")
    public void openDBConnection() {
        DBUtils.getConnection();
    }

    @After("@DB")
    public void closeDBConnection() {
        DBUtils.closeConnection();
    }
}
