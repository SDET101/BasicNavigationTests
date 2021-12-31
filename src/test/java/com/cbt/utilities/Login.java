package com.cbt.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class Login {

    public static void login(String userName, String passWord, String id_user, String id_pswd, String id_login, WebDriver driver){
        driver.findElement(By.id(id_user)).sendKeys(userName);
        driver.findElement(By.id(id_pswd)).sendKeys(passWord);
        driver.findElement(By.id(id_login)).click();

    }
}
