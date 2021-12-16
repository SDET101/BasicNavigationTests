package com.cbt.tests.week02;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBoxLogin {
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
    String loginUrl="http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx";
    String urlAfterLogin="http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/";


    @Test
    public void login(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get(loginUrl);
        driver.manage().window().maximize();

        WebElement usernameInputBox = driver.findElement(By.cssSelector("#ctl00_MainContent_username"));
        WebElement passwordInputBox = driver.findElement(By.cssSelector("#ctl00_MainContent_password"));
        WebElement loginButton = driver.findElement(By.id("ctl00_MainContent_login_button"));

        usernameInputBox.sendKeys(username);
        passwordInputBox.sendKeys(password);
        loginButton.click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, urlAfterLogin, "verify that login successfully");



    }


}
