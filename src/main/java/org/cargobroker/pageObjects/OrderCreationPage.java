package org.cargobroker.pageObjects;

import lombok.extern.log4j.Log4j2;
import org.cargobroker.utils.PageUtils;
import org.cargobroker.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;

@Log4j2
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
        List<HashMap<String, String>> params = Utils.parseJson(Utils.getProperty("pathToOrderParams"));

        if (params == null) {
            log.error("can't retrieve order params");
            return;
        }

        log.info("retrieved " + params.size() + " params");

        for (HashMap<String, String> param : params) {
            log.info("filling param '" + param.get("selector") + "' with value '" + param.get("value") + "'");

            WebElement element = getDriver().findElement(By.cssSelector(param.get("selector")));

            if (param.get("type").equals("selectableOption")) {
                selectItemFromDropdown(element, param.get("value"));
            } else {
                /*
                 * case "text":
                 * case "address":
                 * case "date":
                 * case "price":
                 */
                enterFieldValue(element, param.get("value"));
            }
        }
    }
}