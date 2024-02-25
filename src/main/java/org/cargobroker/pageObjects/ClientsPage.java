package org.cargobroker.pageObjects;

import org.cargobroker.utils.PageUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClientsPage extends PageUtils {
    @FindBy(css = ".quark-button.block.ok[href=\"/client/create\"]")
    private WebElement createClient;
    @FindBy(css = "div[class=\"quark-message ok fa fa-check-circle\"]")
    private WebElement clientSuccessfullyCreatedMessage;

    public ClientsPage() {
        initWebElements();
    }

    public void createNewClient() {
        clickOnElement(createClient);
    }

    public String verifyClientConfirmationMessage() {
        waitWebElement(clientSuccessfullyCreatedMessage);
        return clientSuccessfullyCreatedMessage.getText();
    }

}
