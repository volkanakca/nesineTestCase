package com.NesineTest.automation.stepDefinations;

import com.NesineTest.automation.driver.CurrentPageContext;
import com.NesineTest.automation.driver.PageGenarator;
import com.NesineTest.automation.pages.Header;
import com.NesineTest.automation.restCall.RestCall;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.http.HttpResponse;

import java.util.List;

public class RestCallStepDef {
    public RestCall restCall() {
        PageGenarator base = new PageGenarator();
        CurrentPageContext.setCurrentPage(base.GetInstance(RestCall.class));
        return CurrentPageContext.getCurrentPage().As(RestCall.class);
    }

    public Header header() {
        PageGenarator base = new PageGenarator();
        CurrentPageContext.setCurrentPage(base.GetInstance(Header.class));
        return CurrentPageContext.getCurrentPage().As(Header.class);
    }

    private HttpResponse response; // HttpResponse'ı saklamak için bir değişken tanımlanır


    @And("I send post request popular bets")
    public void sendPostRequestPopulerBets() {
        response = restCall().sendPost("https://www.nesine.com/Iddaa/PopularBetsModal", "{\"eventType\":1,\"date\":null}", "application/json");
    }

    @Then("The response should have status code {int}")
    public void theResponseShouldHaveStatusCode(int statusCodes) {
        int statusCode = response.getStatusLine().getStatusCode();
        restCall().statusCodeControl(statusCodes, statusCode);
    }

    @And("Verify popular bets ui and backend played count and code")
    public void verifyPopularBetsPlayedCountAndCode() {
        List<Integer> uiPlayedCount = header().getPlayedCountsUIList();
        List<Integer> uiMathcCode = header().getMatchCodesUIList();
        restCall().verifyPopulerBetsCodeAndPlayedCount(response, uiPlayedCount, uiMathcCode);
    }
}
