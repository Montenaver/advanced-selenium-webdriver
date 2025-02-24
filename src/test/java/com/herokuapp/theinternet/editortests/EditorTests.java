package com.herokuapp.theinternet.editortests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.EditorPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditorTests extends TestUtilities {

    @Test
    public void defaultEditorValueTest(){
        log.info("defaultEditorValueTest");

        //open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);
        welcomePage.openPage();

        //Scroll to the bottom
        sleep(5000);
        welcomePage.scrollToBottom();
        sleep(5000);

        //click on WYSIWYG Editor link
        EditorPage editorPage = welcomePage.clickWYSIWYGEditorLink();

        //Get default editor text
        String editorText = editorPage.getEditorText();

        //Verify that new page contains expected text in source
        Assert.assertTrue(editorText.equals("Your content goes here."), "Editor default text is not correct." +
                "\nIt is: " + editorText);

//        // Verification that new page contains expected text in source
//        Assert.assertTrue(editorText.equals("Your content goes here."),
//                "Editor default text is not expected. It is: " + editorText);
    }
}
