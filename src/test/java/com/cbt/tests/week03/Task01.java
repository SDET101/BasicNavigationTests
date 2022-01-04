package com.cbt.tests.week03;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task01 extends Object {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("Registration Form")).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
     /*
    Test case #1
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Enter “wrong_dob” into date of birth input box.
    Step 4. Verify that warning message is displayed:
    “The date of birth is not valid”
     */

    @Test
    public void test1(){
        driver.findElement(By.name("birthday")).sendKeys("wrong_dob");
        String actualText = driver.findElement(By.xpath("//*[@data-bv-result='INVALID']")).getText();

        Assert.assertEquals(actualText, "The date of birth is not valid", "verify the warning text for birthday input box");
    }

    /*
    Test case #2
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Verify that following options for
    programming languages are displayed: c++, java, JavaScript
     */

    @Test
    public void test2(){
        List<WebElement> elements = driver.findElements(By.className("form-check-label"));
        List<String> test = new ArrayList<> (Arrays.asList("C++", "Java", "JavaScript"));
        for (WebElement element : elements) {
            test.remove(element.getText());
        }

        Assert.assertEquals(test.size(),0, "verify all language exist");
    }

    /*
    Test case #3
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Enter only one alphabetic character into first name input box.
    Step 4. Verify that warning message is displayed:
    “first name must be more than 2 and less than 64 characters long”
     */

    @Test
    public void test3(){
        driver.findElement(By.name("firstname")).sendKeys("a");
        String actualText = driver.findElement(By.xpath("//*[@data-bv-result='INVALID']")).getText();

        Assert.assertEquals(actualText, "first name must be more than 2 and less than 64 characters long",
                "verify the warning text for firstname input box");
    }

    /*
    Test case #4
    Step 1. Go to https://practice-cybertekschool.herokuapp.com
    Step 2. Click on “Registration Form”
    Step 3. Enter only one alphabetic character into last name input box.
    Step 4. Verify that warning message is displayed:
    “The last name must be more than 2 and less than64 characters long”
      */

    @Test
    public void test4(){
        driver.findElement(By.name("lastname")).sendKeys("a");
        String actualText = driver.findElement(By.xpath("//*[@data-bv-result='INVALID']")).getText();

        Assert.assertEquals(actualText, "The last name must be more than 2 and less than 64 characters long",
                "verify the warning text for lastname input box");
    }

    /*
    Test case #5
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Enter any valid first name.
    Step 4. Enter any valid last name.
    Step 5. Enter any valid user name.
    Step 6. Enter any valid password.
    Step 7. Enter any valid phone number.
    Step 8. Select gender.
    Step 9. Enter any valid date of birth.
    Step 10. Select any department.
    Step 11. Enter any job title.
    Step 12. Select java as a programming language.
    Step 13. Click Sign up.
    Step 14. Verify that following success message is
    displayed: “You've successfully completed registration!”
     */

    @Test
    public void test5() {
        driver.findElement(By.name("firstname")).sendKeys("hasan");
        driver.findElement(By.name("lastname")).sendKeys("oz");
        driver.findElement(By.name("username")).sendKeys("hasanoz");
        driver.findElement(By.name("email")).sendKeys("abc@gmail.com");
        driver.findElement(By.name("password")).sendKeys("abchasan");
        driver.findElement(By.name("phone")).sendKeys("555-355-3535");
        driver.findElement(By.xpath("//*[@value='male']")).click();
        driver.findElement(By.name("birthday")).sendKeys("01/01/1974");

        WebElement dropDownElement = driver.findElement(By.name("department"));
        Select departmentDropDown = new Select(dropDownElement);

        String expectedOption = "Select your Department/Office";
        String actualOption = departmentDropDown.getFirstSelectedOption().getText();

        Assert.assertEquals(actualOption, expectedOption, "verify the displayed department option");

        departmentDropDown.selectByVisibleText("Accounting Office");

        WebElement jobTitleElement = driver.findElement(By.name("job_title"));
        Select jobTitleDropDown = new Select(jobTitleElement);

        expectedOption = "Select job type";
        actualOption = jobTitleDropDown.getFirstSelectedOption().getText();

        Assert.assertEquals(actualOption, expectedOption, "verify the displayed job title option");

        jobTitleDropDown.selectByIndex(4);

        driver.findElement(By.id("inlineCheckbox2")).click();
        driver.findElement(By.id("wooden_spoon")).click();

        String expectedResult = "You've successfully completed registration!";
        String actualResult = driver.findElement(By.xpath("//p")).getText();

        Assert.assertEquals(actualResult, expectedResult, "verify signed successfully");
    }
}
