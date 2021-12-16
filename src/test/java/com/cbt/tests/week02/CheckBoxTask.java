package com.cbt.tests.week02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class CheckBoxTask {

    /*
    Test Case Verify CheckBox CheckAll and UncheckAll Buttons
    1. Go to http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx
    2. Login with-----Username: Tester, password: test
    3. Click on check all button verify all the checkboxes are checked
    4. Click on uncheck all button verify that all the checkboxes are unchecked
    5. Select one of the checkbox and delete one person
    6. Then verify that deleted item is no longer exists
     */

    WebDriver driver;
    String username="Tester";
    String password="test";

    @Test
    public void verifyCheckBox()  {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        WebElement usernameInputBox = driver.findElement(By.cssSelector("#ctl00_MainContent_username"));

        usernameInputBox.sendKeys(username);

        WebElement passwordInputBox = driver.findElement(By.cssSelector("#ctl00_MainContent_password"));

        passwordInputBox.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginButton.click();

        String url = driver.getCurrentUrl();
        Assert.assertEquals(url,"http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/","verify that login successfully");

        int row_count = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr")).size();

        isAllUnchecked(row_count);

        WebElement checkAllButton = driver.findElement(By.id("ctl00_MainContent_btnCheckAll"));
        checkAllButton.click();

        isAllChecked(row_count);

        WebElement uncheckAllButton = driver.findElement(By.id("ctl00_MainContent_btnUncheckAll"));
        uncheckAllButton.click();

        isAllUnchecked(row_count);

        Random rn = new Random();

        int index = rn.nextInt(row_count-1)+2;

        WebElement person = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+index+"]"));
        String expectedResult = person.getText();

        driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+index+"]//input")).click();

        driver.findElement(By.cssSelector("#ctl00_MainContent_btnDelete")).click();

        row_count = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr")).size();

        Assert.assertFalse(isExist(row_count, expectedResult));

        driver.close();

    }

    public void isAllUnchecked(int row_count) {
        int i=2;

        while(i<=row_count){
            WebElement checkBoxPerson = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+i+"]//input"));

            Assert.assertFalse(checkBoxPerson.isSelected(),"verify that check box is unchecked");
            i++;
        }

    }

    public void isAllChecked(int row_count){
        int i=2;

        while(i<=row_count){
            WebElement checkBoxPerson = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+i+"]//input"));

            Assert.assertTrue(checkBoxPerson.isSelected(),"verify that check box is unchecked");
            i++;
        }
    }

    public boolean isExist(int row_count, String expectedResult){
        int i=2;

        while(i<=row_count){
            WebElement eachPerson = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+i+"]"));

            if(expectedResult.equals(eachPerson.getText())){
                return true;
            }
            i++;
        }
        return false;
    }

}
