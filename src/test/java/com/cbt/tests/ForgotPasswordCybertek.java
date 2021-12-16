package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordCybertek {

    public static void main(String[] args) throws InterruptedException {

        /*
        XPATH PRACTICES
DO NOT USE ANY DEVELOPER TOOLS TO GET ANY LOCATORS.
1. Open Chrome browser
2. Go to http://practice.cybertekschool.com/forgot_password
3. Locate all the WebElements on the page using XPATH locator only (total of 6)
   a. “Home” link
   b. “Forgot password” header
   c. “E-mail” text
   d. E-mail input box
   e. “Retrieve password” button
4.Print text of a,b,c,e and put some email to d
         */

        WebDriver driver = BrowserFactory.getDriver("chrome");

        driver.get("http://practice.cybertekschool.com/forgot_password");

        WebElement homeLink = driver.findElement(By.xpath("//a[@class='nav-link']"));
        WebElement forgottenPasswordHeader = driver.findElement(By.xpath("//h2[text()='Forgot Password']"));
        WebElement emailText = driver.findElement(By.xpath("//label[@for='email']"));
        WebElement emailInputBox = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement retrievePasswordButton = driver.findElement(By.xpath("//button[@id='form_submit']"));

        System.out.println(homeLink.getText());
        System.out.println(forgottenPasswordHeader.getText());
        System.out.println(emailText.getText());
        emailInputBox.sendKeys("mike@cydeo.com");
        System.out.println(retrievePasswordButton.getText());

        Thread.sleep(2000);

        driver.close();

    }
}
