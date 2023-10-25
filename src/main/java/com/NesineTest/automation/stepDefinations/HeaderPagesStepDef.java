package com.NesineTest.automation.stepDefinations;

import com.NesineTest.automation.driver.CurrentPageContext;
import com.NesineTest.automation.driver.PageGenarator;
import com.NesineTest.automation.pages.Header;
import io.cucumber.java.en.And;

public class HeaderPagesStepDef extends PageGenarator {
    public Header header() {
        PageGenarator base = new PageGenarator();
        CurrentPageContext.setCurrentPage(base.GetInstance(Header.class));
        return CurrentPageContext.getCurrentPage().As(Header.class);
    }

    @And("Check header at {string} page")
    public void checkHamburgerMenuPage(String headerTabs) {
        header().checkVisetedHamburgerMenuTab(headerTabs);
    }

    @And("I click futbol bets")
    public void clickFutbolTabs() {
        header().clickFutbolTabs();
    }
}
