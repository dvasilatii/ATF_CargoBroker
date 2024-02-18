package org.cargobroker;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class StandAloneTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://cargo.l7n.ru/");

        //enter creds and login
        driver.findElement(By.name("email")).sendKeys("bennyokda@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Chisinau08");
        driver.findElement(By.cssSelector(".quark-button")).click();

        //open order creation page and click on create
        driver.findElement(By.cssSelector("#presence-menu-side-parent a[href=\"/order/list?type=new\"]")).click();
        driver.findElement(By.xpath("//a[@href=\"/order/create\"]")).click();

        //enter params for new order
        WebElement staticDropdownOriginCountry = driver.findElement(By.cssSelector("select[name=\"param[id_14]\"]"));
        Select dropdownOriginCountry = new Select(staticDropdownOriginCountry);
        dropdownOriginCountry.selectByVisibleText("Moldova");

        WebElement staticDropdownDestinationCountry = driver.findElement(By.name("param[id_1]"));
        Select dropdownDestinationCountry = new Select(staticDropdownDestinationCountry);
        dropdownDestinationCountry.selectByVisibleText("Japan");

        driver.findElement(By.xpath("//input[@name='param[id_2]']"))
                .sendKeys("Alba-Iulia 188/54");
        driver.findElement(By.name("param[id_4]")).sendKeys("Yamada 361");

        driver.findElement(By.cssSelector("textarea[placeholder='Physical characteristics']"))
                .sendKeys("big cargo");
    }
}
