package com.NesineTest.automation.pages;

import com.NesineTest.automation.driver.PageGenarator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class Header extends PageGenarator {

    private final By matchCodes = By.xpath("//div[@data-test-id=\"BetList\"]//div[@class=\"matchCode\"]//span");
    private final By playedCount = By.xpath("//div[@data-test-id=\"BetList\"]//div[@class=\"playedCount\"]");

    @FindBy(how = How.XPATH, using = "//h1[@class=\"pageTitle\" and text()=\"POPÜLER BAHİSLER\"]")
    private WebElement popularBetsTitle;
    @FindBy(how = How.XPATH, using = "//h1[@class=\"page-title\" and text()=\"EDİTÖR YORUMLARI\"]")
    private WebElement editorsTitle;
    @FindBy(how = How.XPATH, using = "//button[@data-testid=\"nsn-button\" and text()=\"Futbol\"]")
    private WebElement popularBetsFutbolTabs;
    @FindBy(how = How.XPATH, using = "//button[@class=\"nsn-btn active\" and text()=\"Futbol\"]")
    private WebElement activePopularBetsFutbolTabs;

    public void checkVisetedHamburgerMenuTab(String tabName) {
        switch (tabName) {
            case "popularBets":
                if (!checkUrlIsOpened("https://www.nesine.com/iddaa/populer-bahisler") || !checkElementIsDisplayed(popularBetsTitle)) {
                    fail("Populer bets page not open");
                }
                break;
            case "editors":
                if (!checkUrlIsOpened("https://www.nesine.com/iddaa/futbol/editor-yorumlari#1-1000-Coupons") || !checkElementIsDisplayed(editorsTitle)) {
                    fail("Editors  page not open");
                }
                break;
            default:
                fail("Hamburger menu name not found");
        }

    }

    public void clickFutbolTabs() {
        try {
            waitUntilElementClickable(popularBetsFutbolTabs);
            popularBetsFutbolTabs.click();
            waitUntilElementVisible(activePopularBetsFutbolTabs);
        } catch (Exception e) {
            fail("clickFutbolTabs is not working" + e.getMessage());
        }
    }

    public List<Integer> getMatchCodesUIList() {
        List<WebElement> matchCodesUIList = waitList(matchCodes, 5);
        List<Integer> matchCodesIntList = new ArrayList<>();
        for (WebElement matchCodeElement : matchCodesUIList) {
            int matchCodeInt = Integer.parseInt(matchCodeElement.getText());
            matchCodesIntList.add(matchCodeInt);
        }
        return matchCodesIntList;
    }

    public List<Integer> getPlayedCountsUIList() {
        List<WebElement> playedCountsUIList = waitList(playedCount, 5);
        List<Integer> matchPlayedCountIntList = new ArrayList<>();
        for (WebElement playedCountElement : playedCountsUIList) {
            int matchCodeInt = Integer.parseInt(playedCountElement.getText().replaceAll("\\.",""));
            matchPlayedCountIntList.add(matchCodeInt);
        }
        return matchPlayedCountIntList;
    }


}
