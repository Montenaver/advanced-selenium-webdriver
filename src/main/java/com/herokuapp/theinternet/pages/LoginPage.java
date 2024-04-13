package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage extends BasePageObject {
    private String pageUrl = "https://the-internet.herokuapp.com/login";
    private By usernameLocator = By.id("username");
    private By passwordLocator = By.id("password");
    private By loginBtnLocator = By.className("radius");
    private By errorMessageLocator = By.id("flash");
    public LoginPage(WebDriver driver, Logger log){
        super(driver, log);
    }


    public String getPageUrl() {
        return pageUrl;
    }

    public boolean isLoginBtnVisible(){
        return find(loginBtnLocator).isDisplayed();
    }

    public String getErrorMessageText(){
        waitForVisibilityOf(errorMessageLocator,  Duration.ofSeconds(5));
        return find(errorMessageLocator).getText();
    }

    public LoginPage negativeLogin(String username, String password){
        log.info("Executing Login with username [" + username + "] and password [" + password + "]");
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(loginBtnLocator);
        return this;
    }

    public SecureAreaPage login(String username, String password){
        log.info("Executing Login with username [" + username + "] and password [" + password + "]");
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(loginBtnLocator);
        return new SecureAreaPage(driver, log);
    }
}
