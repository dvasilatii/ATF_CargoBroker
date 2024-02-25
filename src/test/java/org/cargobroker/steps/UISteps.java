package org.cargobroker.steps;

import com.github.javafaker.Faker;
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
    //TODO: don't initialize at the same time

    @Given("user log in")
    public void userLogIn() {
        loginPage.enterEmail(Utils.getProperty("username"));
        loginPage.enterPassword(Utils.getProperty("password"));
        loginPage.clickOnLogin();
    }

    @And("user navigates to {string} section")
    public void userNavigatesToSection(String section) {
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
    @And("\"create\" new client button is clicked")
    public void createClient() {
        clientsPage.createNewClient();
    }

    @When("user fills client details")
    public void userFillsClientDetails() {
        Faker faker = new Faker();
        clientCreationPage.fillOrganizationDetails(
                faker.company().name(),
                faker.name().fullName()
        );
        clientCreationPage.fillContactDetails(
                faker.internet().emailAddress(),
                faker.regexify("\\+[0-9]{11,}"),
                faker.address().fullAddress()
        );
    }

    @Then("user submits new client")
    public void userSubmitsNewClient() {
        clientCreationPage.createClientWithParams();
    }

    @And("{string} message is displayed on Clients page")
    public void clientSuccessfullyCreatedMessageIsDisplayedOnClientsPage(String message) {
        String clientConfirmMessage = clientsPage.verifyClientConfirmationMessage();
        Assert.assertTrue(clientConfirmMessage.equalsIgnoreCase(message));
    }
}
