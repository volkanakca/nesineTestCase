package com.NesineTest.automation.driver;


import com.NesineTest.automation.config.Settings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;

public class DriverFactory {

    public DriverFactory() {
    }

    public static WebDriver InitializeBrowser() throws MalformedURLException {
        WebDriver driver = null;
        if (driver == null) {

                switch (Settings.BrowserType) {
                    case "Chrome":
                        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                        ChromeOptions co=new ChromeOptions();
                        co.addArguments("--remote-allow-origins=*");
                        //co.addArguments("--headless");
                        //co.addArguments("--window-size=1920x1080");
                        driver = new ChromeDriver(co);
                        break;
                    case "FireFox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;

                }

        }
        LocalDriverContext.setDriver(driver);
        return driver;
    }
}
