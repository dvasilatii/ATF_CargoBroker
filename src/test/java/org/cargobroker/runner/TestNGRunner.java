package org.cargobroker.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/featureFiles",
        glue = {"org.cargobroker.steps", "org.cargobroker.hooks"},
//        monochrome = true,
        tags = "@Run",
        plugin = {"pretty", "html:target/reports/cucumber-report.html"}
)
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
