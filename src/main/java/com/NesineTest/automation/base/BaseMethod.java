package com.NesineTest.automation.base;

import com.NesineTest.automation.driver.LocalDriverContext;
import io.qameta.allure.Step;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static com.NesineTest.automation.driver.LocalDriverContext.getDriver;

public class BaseMethod {
    final Logger driverContextLogger = LogManager.getLogger(BaseMethod.class);
    long timeOutInSeconds = 90L;


    public String getJSONFromFile(String path) {
        String jsonText = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText += line + "\n";
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonText;
    }


    public String getUser(String memberOrPassword, String usersName) {
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(getJSONFromFile("src/main/java/com/NesineTest/automation/users/" + usersName + ".json"));
            JSONObject mainJsonObject = (JSONObject) object;
            String data = (String) mainJsonObject.get(memberOrPassword);
            return data;
        } catch (Exception e) {
            Assert.fail("Error getJsonString");
            return "Error getJsonString";
        }

    }
    protected void hoverElementWithActions(WebElement webElement) {
            Actions actions = new Actions(getDriver());
            actions.moveToElement(webElement).build().perform();
    }

    public void waitUntilElementVisible(WebElement elementFindBy) {
        try {
            FluentWait<WebDriver> wait = (new WebDriverWait(LocalDriverContext.getDriver(), Duration.ofSeconds(this.timeOutInSeconds))).pollingEvery(Duration.ofSeconds(5L)).withTimeout(Duration.ofSeconds(this.timeOutInSeconds)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOf(elementFindBy));
        } catch (Throwable var3) {
            this.driverContextLogger.error(ExceptionUtils.getMessage(var3));
            Assert.fail("Element: " + elementFindBy + " WebElement gorunur degil !!");
        }

    }

    public boolean checkElementIsDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Throwable var3) {
            return false;
        }
    }

    public void waitForPageToLoad() {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriverContext.getDriver(), Duration.ofSeconds(20));
            JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getDriver();
            ExpectedCondition jsLoad = (webDriver) -> {
                return jsExecutor.executeScript("return document.readyState", new Object[0]).toString().equals("complete");
            };

            String jsReadyState;
            do {
                wait.until(jsLoad);
                jsReadyState = jsExecutor.executeScript("return document.readyState", new Object[0]).toString();
            } while (!jsReadyState.equalsIgnoreCase("complete"));

            ExpectedCondition jQueryLoad = (webDriver) -> {
                return (Boolean) jsExecutor.executeScript("return window.jQuery != undefined && jQuery.active === 0", new Object[0]);
            };

            String jQueryCount;
            do {
                wait.until(jQueryLoad);
                jQueryCount = jsExecutor.executeScript("return jQuery.active", new Object[0]).toString();
            } while (Integer.parseInt(jQueryCount) != 0);

        } catch (Throwable var6) {
            this.driverContextLogger.error(ExceptionUtils.getMessage(var6));
        }
    }


    public boolean checkUrlIsOpened(String url) {
        try {
            Iterator tabIterator = getDriver().getWindowHandles().iterator();

            do {
                if (!tabIterator.hasNext()) {
                    return false;
                }

                String eachTab = (String) tabIterator.next();
                getDriver().switchTo().window(eachTab);
            } while (!getDriver().getCurrentUrl().trim().equalsIgnoreCase(url.trim()));

            return true;
        } catch (Throwable var4) {
            this.driverContextLogger.error(ExceptionUtils.getMessage(var4));
            Assert.fail("URL: " + url + " ERROR occurred while opening the specified connection !!");
            return false;
        }
    }

    public void findScrollElementCenter(WebElement webElement) {
        try {
            String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
            ((JavascriptExecutor) getDriver()).executeScript(scrollElementIntoMiddle, webElement);
        } catch (Exception e) {
            fail(webElement + " scroll edilirken problem oluştu.");
        }
    }

    public void waitUntilElementClickable(WebElement elementFindBy) {
        try {
            FluentWait<WebDriver> wait = (new WebDriverWait(LocalDriverContext.getDriver(), Duration.ofSeconds(10))).pollingEvery(Duration.ofSeconds(5L)).withTimeout(Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.elementToBeClickable(elementFindBy));
        } catch (Throwable var3) {
            this.driverContextLogger.error(ExceptionUtils.getMessage(var3));
            Assert.fail("Element: " + elementFindBy + " WebElement click edilebilir durumda degil !!");
        }

    }

    public void fail(String text) {
        driverContextLogger.error(text);
        Assert.fail(text);
    }

    public void info(String text) {
        driverContextLogger.info(text);
    }

    public List<WebElement> waitList(By locator, int seconds) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(seconds)).pollingEvery(Duration.ofMillis(500L)).ignoring(NotFoundException.class).ignoring(NoSuchElementException.class);
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

}
