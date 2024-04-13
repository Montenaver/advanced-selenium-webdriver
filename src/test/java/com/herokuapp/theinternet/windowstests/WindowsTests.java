package com.herokuapp.theinternet.windowstests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.NewWindowPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import com.herokuapp.theinternet.pages.WindowsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WindowsTests extends TestUtilities {

    @Test
    public void newWindowTest(){
        log.info("Starting newWindowTest");

        //open main page and click on MultipleWindows Link
        WelcomePage welcomePage = new WelcomePage(driver, log);
        WindowsPage windowsPage = welcomePage.openPage().clickmultipleWindowsLink();

        //click 'Click Here' link to open a new window
        windowsPage.openNewWindow();

        //Find and switch to new window page
        NewWindowPage newWindowPage = windowsPage.switchToNewWindowPage();

        String pageSource = newWindowPage.getCurrentPageSource();

        //verification that new page contains expected text in source
        Assert.assertTrue(pageSource.contains("New Window"), "Oops! The page source doesn't contain" +
                "\"New Window\" title.");
    }
}
