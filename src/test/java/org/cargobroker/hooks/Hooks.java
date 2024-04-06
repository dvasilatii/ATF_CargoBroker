package org.cargobroker.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.cargobroker.context.DataKeys;
import org.cargobroker.context.ScenarioContext;
import org.cargobroker.utils.DBUtils;
import org.cargobroker.utils.PageUtils;
import org.cargobroker.utils.Utils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {
    @BeforeAll
    public static void setRunTime() {
        String id = DateTimeFormatter.ofPattern(Utils.getProperty("screenshotDateFormat")).format(LocalDateTime.now());
        ScenarioContext.getScenarioInstance().saveData(DataKeys.TEST_RUN_TIME, id);

        File evidence = new File(Utils.getProperty("pathToEvidence") + id);
        evidence.mkdirs();
    }

    @Before("@Run")
    public void setCurrentTestName(Scenario scenario) {
        String name = scenario.getName().replace(" ", "_");
        ScenarioContext.getScenarioInstance().saveData(DataKeys.TEST_CURRENT, name);

        String id = ScenarioContext.getScenarioInstance().getData(DataKeys.TEST_RUN_TIME);

        File evidence = new File(Utils.getProperty("pathToEvidence") + id + "/" + name);
        evidence.mkdirs();
    }

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

        PageUtils.quit();
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