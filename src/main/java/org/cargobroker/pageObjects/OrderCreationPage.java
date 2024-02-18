package org.cargobroker.pageObjects;

import org.cargobroker.utils.PageUtils;
import org.cargobroker.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;

public class OrderCreationPage extends PageUtils {
    @FindBy(css = ".quark-input.im-dropdown-subject")
    private WebElement selectClientDropdown;
    @FindBy(css = "select[name=\"department\"]")
    private WebElement selectDepartment;
    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement submitNewOrderButton;

    public OrderCreationPage() {
        initWebElements();
    }
    public void selectClient(String clientName, String department) {
        selectClientDropdown.sendKeys(clientName);
        selectItemFromDropdown(selectDepartment, department);
    }
    public void submitOrder() {
        clickOnElement(submitNewOrderButton);
    }

    public void fillOrderParams() {
        List<HashMap<String,String>> params = Utils.parseJson(
                System.getProperty("user.dir") + "/src/test/resources/jsonData/newOrderParams.json"
        );

        for (HashMap<String,String> param : params) {
            WebElement element = getDriver().findElement(By.cssSelector(param.get("selector")));

            switch (param.get("type")) {
                case "selectableOption":
                    selectItemFromDropdown(element, param.get("value"));
                    break;
                /*case "text":
                case "address":
                case "date":
                case "price":*/
                default:
                    enterFieldValue(element, param.get("value"));
                    break;
            }
        }
    }

}
