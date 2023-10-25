package com.NesineTest.automation.stepDefinations;

import com.NesineTest.automation.base.UsersConfig;
import com.NesineTest.automation.driver.CurrentPageContext;
import com.NesineTest.automation.driver.PageGenarator;
import com.NesineTest.automation.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class LoginPagesStepDef {

    public LoginPage loginPage() {
        PageGenarator base = new PageGenarator();
        CurrentPageContext.setCurrentPage(base.GetInstance(LoginPage.class));
        return CurrentPageContext.getCurrentPage().As(LoginPage.class);
    }

    public UsersConfig usersConfig() {
        PageGenarator base = new PageGenarator();
        CurrentPageContext.setCurrentPage(base.GetInstance(UsersConfig.class));
        return CurrentPageContext.getCurrentPage().As(UsersConfig.class);
    }

    @Given("I login {string} member")
    public void loginMember(String member) {
        loginPage().loginUsers(usersConfig().getMember(member), usersConfig().getPassword(member));
    }

    @And("I logout nesine")
    public void ıLogoutNesine() {
        loginPage().logout();
    }

}
