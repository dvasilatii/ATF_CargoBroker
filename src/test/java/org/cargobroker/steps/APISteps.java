package org.cargobroker.steps;

import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.cargobroker.context.DataKeys;
import org.cargobroker.context.ScenarioContext;
import org.cargobroker.utils.APIUtils;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class APISteps {
    private static final ScenarioContext CONTEXT = ScenarioContext.getScenarioInstance();

    @Given("order list is retrieved")
    public void orderListIsRetrieved() {
        Response response = APIUtils.sendRequest("get", "/order/list", null);
        CONTEXT.saveData(DataKeys.API_RESPONSE, response);
        log.info("order list was requested");
    }

    @And("the response status code is {int}")
    public void validateStatusCode(int expectedStatusCode) {
        Response response = CONTEXT.getData(DataKeys.API_RESPONSE);
        log.info("Expected status code is: " + expectedStatusCode + ", actual status code is: " + response.statusCode());
        Assert.assertEquals(
                expectedStatusCode,
                response.statusCode(),
                "Actual status code is not as expected."
        );
    }

    @And("latest order was successfully retrieved")
    public void latestOrderWasSuccessfullyRetrieved() {
        Response response = CONTEXT.getData(DataKeys.API_RESPONSE);
        JsonPath jsonPathEvaluator = response.jsonPath();

        List<Map<String, Object>> orderList = jsonPathEvaluator.getList("");
        Assert.assertTrue(orderList.size() != 0, "List of orders is empty");

        Map<String, Object> latestOrder = orderList.get(0);
        int orderID = (int) latestOrder.get("id");
        log.info("latest order ID is " + orderID);
        CONTEXT.saveData(DataKeys.LATEST_ORDER_ID, orderID);
    }

    @When("bid is placed with required details")
    public void bidIsPlaced(DataTable dataTable) {
        Faker faker = new Faker();
        List<Map<String, String>> bidDetails = dataTable.asMaps();
        Map<String, String> bidDetail = bidDetails.get(faker.number().numberBetween(0, bidDetails.size()));
        int latestOrderID = CONTEXT.getData(DataKeys.LATEST_ORDER_ID);

        Map<String, Object> params = new HashMap<>();
        params.put("value", faker.number().numberBetween(
                Integer.parseInt(bidDetail.get("minAmount")),
                Integer.parseInt(bidDetail.get("maxAmount"))
        ));
        params.put("currency", bidDetail.get("currency"));
        params.put("comment", faker.harryPotter().quote());

        Response response = APIUtils.sendRequest("post", "/order/bid/create/" + latestOrderID, params);

        CONTEXT.saveData(DataKeys.API_RESPONSE, response);
        CONTEXT.saveData(DataKeys.BID_AMOUNT, params.get("value"));
        CONTEXT.saveData(DataKeys.BID_CURRENCY, params.get("currency"));
        CONTEXT.saveData(DataKeys.BID_COMMENT, params.get("comment"));
        log.info("order bid creation is requested");
    }
}
