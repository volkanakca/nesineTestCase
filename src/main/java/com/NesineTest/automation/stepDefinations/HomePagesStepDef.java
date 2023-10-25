package com.NesineTest.automation.stepDefinations;

import com.NesineTest.automation.driver.CurrentPageContext;
import com.NesineTest.automation.driver.PageGenarator;
import com.NesineTest.automation.pages.HomePage;
import io.cucumber.java.en.And;


public class HomePagesStepDef {
    public HomePage homePage() {
        PageGenarator base = new PageGenarator();
        CurrentPageContext.setCurrentPage(base.GetInstance(HomePage.class));
        return CurrentPageContext.getCurrentPage().As(HomePage.class);
    }

    @And("I visit header {string} tabs")
    public void visitTab(String popularBets) {
        homePage().visetedHamburgerMenuTab(popularBets);
    }


}
