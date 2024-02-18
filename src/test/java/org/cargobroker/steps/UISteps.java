package org.cargobroker.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.cargobroker.pageObjects.*;
import org.cargobroker.utils.PageUtils;
import org.cargobroker.utils.Utils;
import org.testng.Assert;

public class UISteps {
    LoginPage loginPage = new LoginPage();
    OrdersPage ordersPage = new OrdersPage();
    OrderCreationPage orderCreationPage = new OrderCreationPage();
    ClientsPage clientsPage = new ClientsPage();
    ClientCreationPage clientCreationPage = new ClientCreationPage();

    @Given("user log in")
    public void userLogIn() {
        loginPage.enterEmail(Utils.getProperty("username"));
        loginPage.enterPassword(Utils.getProperty("password"));
        loginPage.clickOnLogin();
    }

    @And("user navigates to {string} section")
    public void userNavigatesToOrdersSection(String section) {
        PageUtils.navigateTo(section);
    }

    @And("\"create\" new order button is clicked")
    public void createOrder() {
        ordersPage.clickOnCreateOrderButton();
    }

    @When("order params fields are filled")
    public void orderParamsFieldsAreFilled() {
        orderCreationPage.fillOrderParams();
    }

    @And("^existing client (.+) is specified for department (.+)$")
    public void existingClientIsSpecified(String clientName, String department) {
        orderCreationPage.selectClient(clientName, department);
    }

    @Then("user submits order")
    public void userSubmitsOrder() {
        orderCreationPage.submitOrder();
    }

    @And("{string} message is displayed on Orders page")
    public void orderSuccessfullyCreatedMessageIsDisplayed(String message) {
        String orderConfirmMessage = ordersPage.verifyOrderConfirmationMessage();
        Assert.assertTrue(orderConfirmMessage.equalsIgnoreCase(message));
    }
}
