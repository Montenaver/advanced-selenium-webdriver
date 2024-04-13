package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HoversPage extends BasePageObject{

    private final String pageUrl = "https://the-internet.herokuapp.com/hovers";
    private final By avatarLocator = By.xpath("//div[@class='figure']");
    private final By viewProfileLinkLocator = By.xpath(".//a[contains(text(), 'View profile')]");

    public HoversPage(WebDriver driver, Logger log){
        super(driver, log);
    }

    public void openPage(){
        log.info("Opening HoversPage");
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    public void openUserProfile(int i){
        log.info("Opening user " + i + " profile");
        List<WebElement> avatars = findAll(avatarLocator);
        WebElement specifiedUserAvatar = avatars.get(i - 1);// так как индексы начинаются с 0, для второго берем 1
        hoverOverElement(specifiedUserAvatar);
        specifiedUserAvatar.findElement(viewProfileLinkLocator).click();
    }
}
