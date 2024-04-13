package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditorPage extends BasePageObject{
    private By edtiorLocator = By.id("tinymce");
    private By frameLocator = By.tagName("iframe");
            //By.xpkeypresstestath("//iframe[@id='mce_0_ifr']");

    public EditorPage(WebDriver driver, Logger log){
        super(driver, log);
    }

    public String getEditorText(){
        switchToFrame(frameLocator);
        String text = find(edtiorLocator).getText();
        log.info("Text from TinyMCE WYSIWYG Editor: " + text);
        return text;
    }
}
