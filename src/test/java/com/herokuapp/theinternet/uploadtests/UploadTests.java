package com.herokuapp.theinternet.uploadtests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.FileUploadPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadTests extends TestUtilities {

    @Test(dataProvider = "files")
    public void fileUploadTest(int num, String fileName){
//        log.info("Starting fileUploadTest #" + num + " for " + fileName);

        //Open UploadPage with its URL
        FileUploadPage fileUploadPage = new FileUploadPage(driver, log);
        fileUploadPage.openPage();

        //Select file
//        String fileName = "TMX_171.jpg";//Удалим это, так как теперь приходит из параметра
        fileUploadPage.selectFile(fileName);

        //Push upload button
        fileUploadPage.pushUploadButton();

        //Get upload files names
        String fileNames = fileUploadPage.getUploadedFilesNames();

        //Verify the file name
        Assert.assertTrue(fileNames.contains(fileName),
                "Oops! Our file (" + fileName + ") is not one of the uploaded (" +
                "\nIt should be \"TMX_171.jpg\"\nBut it is: " + fileNames);
    }



}
