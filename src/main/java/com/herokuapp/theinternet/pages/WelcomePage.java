package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends BasePageObject {
    private String pageUrl = "https://the-internet.herokuapp.com/";
    private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
    private By checkboxesLinkLocator = By.linkText("Checkboxes");
    private By dropdownLinkLocator = By.linkText("Dropdown");
    private By javaScriptAlertLinkLocator = By.linkText("JavaScript Alerts");
    private By multipleWindowsLinkLocator = By.linkText("Multiple Windows");
    private By editorLinkLocator = By.linkText("WYSIWYG Editor");
    private By keyPressesLinkLocator = By.linkText("Key Presses");
    public WelcomePage(WebDriver driver, Logger log){
        super(driver, log);
    }

    //Open Welcome page with its URL
    public WelcomePage openPage(){
        log.info("Open page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
        return this;
    }

    //Open LoginPage by clicking on Form Authentication Link
    public LoginPage clickFormAuthenticationLink(){
        log.info("Clicking Form Authentication Link on Welcome Page");
        click(formAuthenticationLinkLocator);
        return new LoginPage(driver, log);
    }

    //Open CheckboxesPage by clicking on Checkboxes Link
    public CheckboxesPage clickCheckboxesLink(){
        log.info("Clicking Checkboxes link on Welcome Page");
        click(checkboxesLinkLocator);
        return new CheckboxesPage(driver, log);
    }

    //Open DropdownPage by clicking on Dropdown Link
    public DropdownPage clickDropdownLink(){
        log.info("Clicking Dropdown link on Welcome Page");
        click(dropdownLinkLocator);
        return new DropdownPage(driver, log);
    }

    //Open JavaScriptAlertPage by clicking on JavaScriptAlert Link
    public JavaScriptAlertPage clickJavaScriptAlertLink(){
        log.info("Clicking JavaScriptAlert link on Welcome Page");
        click(javaScriptAlertLinkLocator);
        return new JavaScriptAlertPage(driver, log);
    }

    //Open WindowsPage by clicking on Multiple Windows Link
    public WindowsPage clickmultipleWindowsLink(){
        log.info("Clicking multipleWindows link on Welcome Page");
        click(multipleWindowsLinkLocator);
        return new WindowsPage(driver, log);
    }

    //Open WYSIWYG Editor Page by clicking on WYSIWYG Editor Link
    public EditorPage clickWYSIWYGEditorLink(){
        log.info("Clicking WYSIWYG Editor link on Welcome Page");
        click(editorLinkLocator);
        return new EditorPage(driver, log);
    }

    //Open Key Presses Page by clicking on Key Presses Link
    public KeyPressesPage clickKeyPressesLink(){
        log.info("Clicking Key Presses link on Welcome Page");
        click(keyPressesLinkLocator);
        return new KeyPressesPage(driver, log);
    }
}
