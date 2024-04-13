package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DragAndDropPage extends BasePageObject{
    private String pageUrl = "https://the-internet.herokuapp.com/drag_and_drop";
    private By columnALocator = By.xpath("//div[@id='column-a']");
    private By columnBLocator = By.xpath("//div[@id='column-b']");

    public DragAndDropPage(WebDriver driver, Logger log){
        super(driver, log);
    }

    //Open DragAndDropPage with it's url
    public void openPage(){
        log.info("Opening DragAndDropPage");
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    //Drag A box and drop it on B box
    public void dragAtoB(){
        log.info("Drag and drop A box on B box");
        performDragAndDrop(columnALocator, columnBLocator);
    }

    //Getting Column A text
    public String getColumnAText(){
        String text = find(columnALocator).getText();
        log.info("Column A Text: " + text);
        return text;
    }

    //Getting Column B text
    public String getColumnBText(){
        String text = find(columnBLocator).getText();
        log.info("Column B Text: " + text);
        return text;
    }
}
