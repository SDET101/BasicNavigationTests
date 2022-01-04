package com.cbt.tests.week03;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

public class Task02 {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
    }

    @AfterMethod
    public void tearDown() {
         driver.quit();
    }

    /*
    Test case #6
    Step 1. Go to "https://www.tempmailaddress.com/"
    Step 2. Copy and save email as a string.
    Step 3. Then go to “https://practice-cybertekschool.herokuapp.com”
    Step 4. And click on “Sign Up For Mailing List".
    Step 5. Enter any valid name.
    Step 6. Enter email from the Step 2.
    Step 7. Click Sign Up
    Step 8. Verify that following message is displayed:
    “Thank you for signing up. Click the button below to return to the home page.”
    Step 9. Navigate back to the “https://www.tempmailaddress.com/”
    Step 10. Verify that you’ve received an email from “do-not-reply@practice.cybertekschool.com”
    Step 11. Click on that email to open it.
    Step 12. Verify that email is from: “do-notreply@practice.cybertekschool.com”
    Step 13. Verify that subject is: “Thanks for subscribing to practice.cybertekschool.com!”
     */

    @Test
    public void test1(){
        driver.get("https://www.tempmailaddress.com/");
        String title = driver.getTitle();
        String email = driver.findElement(By.id("email")).getText();
        String name = "hasan";
        driver.navigate().to("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.partialLinkText("Mailing List")).click();
        driver.findElement(By.name("full_name")).sendKeys(name);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("wooden_spoon")).click();
        String actualText = driver.findElement(By.name("signup_message")).getText();
        String expectedText ="Thank you for signing up. Click the button below to return to the home page.";

        Assert.assertEquals(actualText, expectedText, "verify the notification message after signing in");

        String emailTitle = "";
        while (!emailTitle.equals(title)){
            driver.navigate().back();
            emailTitle = driver.getTitle();
        }

        List<WebElement> emailList = driver.findElements(By.xpath("//tbody[@id='schranka']/tr/td"));
        System.out.println(emailList.size());
        List<String> list = new ArrayList<>();

        for (WebElement mail : emailList) {
            list.add(mail.getText().trim());
        }

        Assert.assertTrue(list.contains("do-not-reply@practice.cybertekschool.com"), "verification that email was sent");

        int i = list.indexOf("do-not-reply@practice.cybertekschool.com");
        emailList.get(i).click();

        String newTitle = driver.getTitle();

        Assert.assertNotEquals(newTitle, emailTitle, "verify that open the email");

        String actualSubject = driver.findElement(By.id("predmet")).getText();
        String expectedSubject = "Thanks for subscribing to practice.cybertekschool.com!";

        Assert.assertEquals(actualSubject, expectedSubject, "verify the email subject");

    }

}
