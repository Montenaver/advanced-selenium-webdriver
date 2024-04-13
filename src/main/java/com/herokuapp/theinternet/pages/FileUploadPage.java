package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileUploadPage extends BasePageObject{
    private String pageUrl = "https://the-internet.herokuapp.com/upload";
    private By chooseFileBtnLocator = By.xpath("//input[@id='file-upload']");
    private By uploadBtnLocator = By.xpath("//input[@id='file-submit']");
    private By uploadedFileNameLocator = By.xpath("//div[@id='uploaded-files']");


    public FileUploadPage(WebDriver driver, Logger log){
        super(driver, log);
    }

    //Open FileUploadPage with its URL
    public FileUploadPage openPage(){
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("The page is opened!");
        return this;
        }

    //Select file form Files folder
    public void selectFile(String fileName){
        log.info("Selecting '" + fileName + "' file form Files folder");
        //Select file
//        String filePath = "/home/veranaverniuk/";
        String filePath = System.getProperty("user.dir") + "/src/main/resources/files/" + fileName;
        type(filePath, chooseFileBtnLocator);
        log.info("File selected");
    }

    //Pushing upload button
    public void pushUploadButton(){
        log.info("Click on upload button");
        click(uploadBtnLocator);
    }

    //Get names of uploaded files
    public String getUploadedFilesNames(){
        String names = find(uploadedFileNameLocator).getText();
        log.info("Uploaded files: " + names);
        return names;
    }

}
