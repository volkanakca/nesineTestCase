package com.NesineTest.automation.runner;

import com.NesineTest.automation.base.BaseMethod;
import com.NesineTest.automation.config.ConfigReader;
import com.NesineTest.automation.config.Settings;
import com.NesineTest.automation.driver.DriverFactory;
import com.NesineTest.automation.driver.LocalDriverContext;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.time.Duration;

@CucumberOptions(
        tags = "@Regression",
        features = {"src/test/java/features"},
        glue = {"com/NesineTest/automation/stepDefinations"},
        publish = true,
        plugin = { "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" }
)

public class RunnerTest extends AbstractTestNGCucumberTests{

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite(alwaysRun = true)
    public void Initialize() throws IOException {
        ConfigReader.readBrowserConfig();
    }


    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        try {
            BaseMethod driverContext = new BaseMethod();
            LocalDriverContext.setDriver(DriverFactory.InitializeBrowser());
            LocalDriverContext.getDriver().get(Settings.BaseURL);
            LocalDriverContext.getDriver().manage().window().maximize();
            LocalDriverContext.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        }catch (Exception e){

        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        if(LocalDriverContext.getDriver() != null){
            LocalDriverContext.getDriver().quit();
        }
    }
}
