package com.NesineTest.automation.pages;

import com.NesineTest.automation.driver.PageGenarator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class HomePage extends PageGenarator {

    @FindBy(how = How.XPATH, using = "//a[@href=\"/iddaa/populer-bahisler\" and @tracking-label=\"Header-PopulerBahisler\"]")
    private WebElement hamburgerMenuPopularBets;
    @FindBy(how = How.XPATH, using = "//a[@href=\"/iddaa/futbol/editor-yorumlari\" and @tracking-label=\"Header-YorumMerkezi\"]")
    private WebElement hamburgerMenuEditors;


    public void visetedHamburgerMenuTab(String tabName) {
        try {
            switch (tabName) {
                case "popularBets":
                    hamburgerMenuPopularBets.click();
                    waitForPageToLoad();
                    break;
                case "editors":
                    hamburgerMenuEditors.click();
                    waitForPageToLoad();
                    break;
                default:
                    fail("Hamburger menu not found");
            }
        }catch (Exception e){
            fail("An error occurred in visetedHamburgerMenuTab"+e.getMessage());
        }
    }


}
