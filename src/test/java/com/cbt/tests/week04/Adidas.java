package com.cbt.tests.week04;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Adidas {

    /*
    https://www.demoblaze.com/index.html
• Customer navigation through product categories: Phones, Laptops and Monitors
• Navigate to "Laptop" → "Sony vaio i5" and click on "Add to cart". Accept pop up confirmation.
• Navigate to "Laptop" → "Dell i7 8gb" and click on "Add to cart". Accept pop up confirmation.
• Navigate to "Cart" → Delete "Dell i7 8gb" from cart.
• Click on "Place order".
• Fill in all web form fields.
• Click on "Purchase"
• Capture and log purchase Id and Amount.
• Assert purchase amount equals expected.
• Click on "Ok"
     */

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");
    }

    @AfterMethod
    public void tearDown() {
        //driver.quit();
    }

    @Test
    public void test() throws InterruptedException {

        driver.findElement(By.linkText("Laptops")).click();
        driver.findElement(By.linkText("Sony vaio i5")).click();
        String price = driver.findElement(By.xpath("//h3")).getText();
        driver.findElement(By.linkText("Add to cart")).click();

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert=driver.switchTo().alert();
        alert.accept();

        driver.navigate().to("https://www.demoblaze.com/index.html");
        driver.findElement(By.linkText("Laptops")).click();
        driver.findElement(By.linkText("Dell i7 8gb")).click();
        driver.findElement(By.linkText("Add to cart")).click();

        wait.until(ExpectedConditions.alertIsPresent());

        alert=driver.switchTo().alert();
        alert.accept();

        driver.findElement(By.linkText("Cart")).click();

        JavascriptExecutor js=(JavascriptExecutor)driver;

        WebElement delete = driver.findElement(By.xpath("//tbody[@id='tbodyid']//td[.='Dell i7 8gb']/../td[4]/a"));
       // js.executeScript("arguments[0].click()",delete);

        delete.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//h2/../button")).click();

        WebElement inputName=driver.findElement(By.id("name"));
        inputName.sendKeys("hasan");
    //    js.executeScript("arguments[0].setAttribute('value', 'hasan')", inputName);
        WebElement inputCountry=driver.findElement(By.id("country"));
        inputCountry.sendKeys("turkey");
    //    js.executeScript("arguments[0].setAttribute('value', 'Turkey')", inputCountry);
        WebElement inputCity=driver.findElement(By.id("city"));
        inputCity.sendKeys("kony");
    //    js.executeScript("arguments[0].setAttribute('value', 'konya')", inputCity);
        WebElement inputCard=driver.findElement(By.id("card"));
        js.executeScript("arguments[0].setAttribute('value', '55443322')", inputCard);
        WebElement inputMonth=driver.findElement(By.id("month"));
        js.executeScript("arguments[0].setAttribute('value', '11')", inputMonth);
        WebElement inputYear=driver.findElement(By.id("year"));
        js.executeScript("arguments[0].setAttribute('value', '26')", inputYear);

        WebElement purchaseButton = driver.findElement(By.xpath("//button[@onclick='purchaseOrder()']"));
        js.executeScript("arguments[0].scrollIntoView(true);",purchaseButton);
        js.executeScript("arguments[0].click()",purchaseButton);

        Thread.sleep(3000);
        WebElement result = driver.findElement(By.xpath("//div[@data-has-confirm-button='true']/p"));
        String[] bill=result.getText().split("\n");

        System.out.println(bill[0]);
        System.out.println(bill[1]);

        Assert.assertEquals(bill[1].split(" ")[1],price.split(" ")[0].substring(1), "verify amount is correct");

        driver.findElement(By.xpath("//div[@class='sa-confirm-button-container']/button")).click();


    }

}
