package org.cargobroker.pageObjects;

import org.cargobroker.utils.PageUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClientCreationPage extends PageUtils {
    @FindBy(xpath = "//input[@placeholder=\"Organization]\"]")
    private WebElement organizationField;
    @FindBy(css = "input[placeholder=\"Spokesperson\"]")
    private WebElement spokespersonField;
    @FindBy(css = "//input[@placeholder=\"Contact E-mail\"]")
    private WebElement emailField;
    @FindBy(css = "input[placeholder=\"Contact phone\"]")
    private WebElement contactPhoneField;
    @FindBy(xpath = "//input[@placeholder=\"Contact address\"]")
    private WebElement contactAddressField;
    @FindBy(css = ".quark-button.block.ok[type=\"submit\"]")
    private WebElement createClientButton;
    public ClientCreationPage() {
        initWebElements();
    }

    public void createClientWithParams() {
        clickOnElement(createClientButton);
    }

}
