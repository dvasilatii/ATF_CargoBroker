package org.cargobroker.pageObjects;

import org.cargobroker.utils.PageUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageUtils {

    @FindBy(name = "email")
    private WebElement enterEmailField;
    @FindBy(name = "password")
    private WebElement enterPasswordField;
    @FindBy(css = "button[type=\"submit\"]")
    private WebElement loginButton;

    public LoginPage() {
        initWebElements();
    }

    public void enterEmail(String value) {
        enterFieldValue(enterEmailField, value);
    }

    public void enterPassword(String value) {
        enterFieldValue(enterPasswordField, value);
    }

    public void clickOnLogin() {
        clickOnElement(loginButton);
    }
}
