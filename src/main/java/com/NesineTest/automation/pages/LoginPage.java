package com.NesineTest.automation.pages;

import com.NesineTest.automation.driver.PageGenarator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends PageGenarator {
    @FindBy(how = How.CSS, using = "input[data-test-id=\"header-username-input\"]")
    private WebElement memberInput;
    @FindBy(how = How.CSS, using = "input[data-test-id=\"header-password-input\"]")
    private WebElement passwordInput;
    @FindBy(how = How.CSS, using = "button[data-test-id=\"header-login-btn\"]")
    private WebElement loginBtn;
    @FindBy(how = How.CSS, using = "a[data-testid=\"header-hesabim-btn\"]")
    private WebElement myAccount;
    @FindBy(how = How.CSS, using = "a[data-testid=\"header-hesabim-cikis\"]")
    private WebElement logoutBtn;


    public void loginUsers(String memberName, String memberPassword) {
        try {
            memberInput.sendKeys(memberName);
            passwordInput.sendKeys(memberPassword);
            loginBtn.click();
            waitUntilElementClickable(myAccount);
        } catch (Exception e) {
            fail("An error occurred in loginUsers" + e.getMessage());
        }

    }

    public void logout() {
        try {
            waitUntilElementClickable(myAccount);
            hoverElementWithActions(myAccount);
            logoutBtn.click();
            waitUntilElementVisible(loginBtn);
        } catch (Exception e) {
            fail("An error occurred in logout" + e.getMessage());

        }
    }
}
