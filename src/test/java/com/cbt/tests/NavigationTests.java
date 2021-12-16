package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.StringUtility;
import org.openqa.selenium.WebDriver;

public class NavigationTests {

    public static void main(String[] args) {

        multipleBrowserNavigation("chrome", "https://www.google.com", "https://www.etsy.com");
        multipleBrowserNavigation("firefox", "https://www.google.com", "https://www.etsy.com");
        multipleBrowserNavigation("edge", "https://www.google.com", "https://www.etsy.com");

    }

    public static void multipleBrowserNavigation(String browserType, String url1, String url2){

        WebDriver driver = BrowserFactory.getDriver(browserType);

        driver.get(url1);

        String firstVisitUrl1 = driver.getCurrentUrl();

        driver.navigate().to(url2);

        String firstVisitUrl2 = driver.getCurrentUrl();

        driver.navigate().back();

        String secondVisitUrl1 = driver.getCurrentUrl();

        driver.navigate().forward();

        String secondVisitUrl2 = driver.getCurrentUrl();

        System.out.println(StringUtility.verifyEquals(firstVisitUrl1, secondVisitUrl1));
        System.out.println(StringUtility.verifyEquals(firstVisitUrl2, secondVisitUrl2));

        driver.close();
    }

}
