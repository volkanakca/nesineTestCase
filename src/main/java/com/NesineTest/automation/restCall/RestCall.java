package com.NesineTest.automation.restCall;

import com.NesineTest.automation.driver.PageGenarator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


public class RestCall extends PageGenarator {


    public HttpResponse sendPost(String url, String body, String contentType) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpUriRequest request = new HttpPost(url);
        request.addHeader("Content-Type", contentType);
        try {
            HttpPost httpPost = (HttpPost) request;
            httpPost.setEntity(new StringEntity(body, "UTF-8"));
            HttpResponse postResponse = httpClient.execute(request);
            return postResponse;
        } catch (Exception e) {
            fail("sendPost failed" + e.getMessage());
            return null;
        }
    }

    public void statusCodeControl(int expectedStatusCode, int result) {
        Assert.assertEquals(expectedStatusCode, result, "Status code wrong.");
    }

    public void verifyPopulerBetsCodeAndPlayedCount(HttpResponse response, List<Integer> uiPlayedCount, List<Integer> uiMathcCode) {
        try {

            String responseBody = EntityUtils.toString(response.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);

            JsonNode popularBetList = rootNode.get("PopularBetList");

            List<Integer> playedCountList = new ArrayList<>();
            List<Integer> codeList = new ArrayList<>();

            for (int i = 0; i < 50; i++) {
                JsonNode bet = popularBetList.get(i);
                playedCountList.add(bet.get("PlayedCount").asInt());
                codeList.add(bet.get("MarketNo").asInt());
            }

            if (uiPlayedCount.size() != playedCountList.size() || uiMathcCode.size() != codeList.size()) {
                fail("List sizes are different. No comparison can be made.");
            } else {
                boolean playedCountEqual = uiPlayedCount.equals(playedCountList);
                boolean codeEqual = uiMathcCode.equals(codeList);

                if (playedCountEqual && codeEqual) {
                    info("playedCount and code equals.");
                } else {
                    fail("playedCount and code not equals");
                }
            }

        } catch (Exception e) {
            fail("An error occurred while verifying PopularBetsCodeAndPlayedCount." + e.getMessage());
        }
    }


}
