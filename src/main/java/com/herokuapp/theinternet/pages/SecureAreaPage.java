package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SecureAreaPage extends BasePageObject{
    private String pageUrl = "https://the-internet.herokuapp.com/secure";
    private By logoutBtn = By.xpath("//a[@class='button secondary radius']");
    private By message = By.id("flash");
    private String expectedMessage = "You logged into a secure area!";


    public SecureAreaPage(WebDriver driver, Logger log){
        super(driver, log);
    }

    //Get URL variable from PageObject
    public String getPageUrl(){
        return pageUrl;
    }

    //Verification if logoutBtn is visible on the page
    public boolean isLogoutBtnVisible(){
        return find(logoutBtn).isDisplayed();
    }

    //Return text from success message
    public String getSuccessMessageText(){
        return find(message).getText();
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }
}
