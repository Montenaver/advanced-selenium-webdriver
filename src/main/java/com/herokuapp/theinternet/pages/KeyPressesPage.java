package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class KeyPressesPage extends BasePageObject{
    private String pageUrl = "https://the-internet.herokuapp.com/key_presses";
    private By body = By.xpath("//body");
    private By resultTextLocator = By.xpath("//p[@id='result']");

    public KeyPressesPage(WebDriver driver, Logger log){
        super(driver, log);
    }

    //Open KeyPressPage with its URL
    public void openPage(){
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("The page is opened!");
    }

    //Press given key while on this page
    public void pressKey(Keys key){
        log.info("Pressing " + key.name());
        pressKey(body, key);
    }

    //Get result text
    public String getResultText(){
        String result = find(resultTextLocator).getText();
        log.info("Result text: " + result);
        return result;
    }
}
