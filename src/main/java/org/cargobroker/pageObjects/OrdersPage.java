package org.cargobroker.pageObjects;

import org.cargobroker.utils.PageUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OrdersPage extends PageUtils {

    @FindBy(xpath = "//a[@href=\"/order/create\"]")
    private WebElement createOrderButton;
    @FindBy(css = "div[class=\"quark-message ok fa fa-check-circle\"]")
    private WebElement orderSuccessfullyCreatedMessage;
    @FindBy(css = ".im-entity-list")
    private WebElement orderList;

    public OrdersPage() {
        initWebElements();
    }

    public void clickOnCreateOrderButton() {
        clickOnElement(createOrderButton);
    }

    public String verifyOrderConfirmationMessage() {
        return orderSuccessfullyCreatedMessage.getText();
    }

    public WebElement findOrder(int id) {
        List<WebElement> orders = orderList.findElements(By.cssSelector(".im-entity"));

        for (WebElement order : orders) {
            WebElement head = order.findElement(By.tagName("h4"));
            String[] headParts = head.getText().split(" ");

            if (headParts[0].equals("#" + id)) {
                return order;
            }
        }

        return null;
    }

    public WebElement findBidByDetails(WebElement order, int amount, String currency, String comment) {
        List<WebElement> bids = order.findElements(By.cssSelector(".im-order-list-item-main-bid-list-body-item"));

        for (WebElement bid : bids) {
            WebElement bidValue = bid.findElement(By.cssSelector(".im-order-list-item-main-bid-list-body-item-value"));
            WebElement bidComment = bid.findElement(By.cssSelector(".im-order-list-item-main-bid-list-body-item-meta-comment"));

            if (bidValue.getText().equals(amount + ".00 " + currency) && bidComment.getText().equals(comment)) {
                return bid;
            }
        }

        return null;
    }

    public void showBidsOfOrder(WebElement order) {
        clickOnElement(order.findElement(By.cssSelector(".im-order-list-item-main-bid-list-header-action.show")));
    }
}
