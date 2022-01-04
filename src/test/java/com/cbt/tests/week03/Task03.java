package com.cbt.tests.week03;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Task03 {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    /*
    Test case #7
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. And click on “File Upload".
    Step 3. Upload any file with .txt extension from your computer.
    Step 4. Click “Upload” button.
    Step 5. Verify that subject is: “File Uploaded!”
    Step 6. Verify that uploaded file name is displayed.
    Note: use element.sendKeys(“/file/path”) with specifying path to the file for uploading.
    Run this method against “Choose File” button.
     */

    @Test
    public void testUpload(){
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("File Upload")).click();
        driver.findElement(By.id("file-upload")).sendKeys("C:/Users/eltko/Downloads/car_insurance.txt");
        driver.findElement(By.id("file-submit")).click();
        String text = driver.findElement(By.tagName("h3")).getText();
        String ecpectedText = "File Uploaded!";

        Assert.assertEquals(text, ecpectedText, "verify that file uploaded");

        boolean isFileNameDisplayed = driver.findElement(By.id("uploaded-files")).isDisplayed();
        Assert.assertTrue(isFileNameDisplayed,"verify the file name is displayed");

        String fileName = "car_insurance.txt";
        String actualFileName = driver.findElement(By.id("uploaded-files")).getText();

        Assert.assertEquals(actualFileName, fileName, "verify the file name is match with actual result");

    }
}
