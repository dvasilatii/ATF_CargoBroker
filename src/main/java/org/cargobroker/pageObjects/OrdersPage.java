package org.cargobroker.pageObjects;

import org.cargobroker.utils.PageUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrdersPage extends PageUtils {

    @FindBy (xpath = "//a[@href=\"/order/create\"]")
    private WebElement createOrderButton;
    @FindBy (css = "div[class=\"quark-message ok fa fa-check-circle\"]")
    private WebElement orderSuccessfullyCreatedMessage;

    public OrdersPage() {
        initWebElements();
    }
    public void clickOnCreateOrderButton() {
        clickOnElement(createOrderButton);
    }
    public String verifyOrderConfirmationMessage() {
        return orderSuccessfullyCreatedMessage.getText();
    }

}
