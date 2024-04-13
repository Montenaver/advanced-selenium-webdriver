package com.herokuapp.theinternet.keypresstests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.KeyPressesPage;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KeyPressTests extends TestUtilities {

    @Test
    public void pressKeyTest(){
        log.info("Starting pressKeyTest");

        //open main page and click on Key Presses link
//        WelcomePageObject welcomePageObject = new WelcomePageObject(driver, log);
//        KeyPressesPage keyPressesPage = welcomePageObject.openPage().clickKeyPressesLink();
        KeyPressesPage keyPressesPage = new KeyPressesPage(driver, log);
        keyPressesPage.openPage();

        //Push keyboard key
        keyPressesPage.pressKey(Keys.ENTER);

        //Get Results text
        String result = keyPressesPage.getResultText();

        //Verify Result text is as expected
        Assert.assertTrue(result.equals("You entered: ENTER"), "The result should be: \"You entered: ENTER\"" +
                "\nBut it is actually is: " + result);
    }
    @Test
    public void pressKeyWithActionsTest(){
        log.info("Starting pressKeyWithActionsTest");

        //open Key Presses Page
        KeyPressesPage keyPressesPage = new KeyPressesPage(driver, log);
        keyPressesPage.openPage();

        //Push keyboard key
        keyPressesPage.pressKeyWithActions(Keys.SPACE);

        //Get Results text
        String result = keyPressesPage.getResultText();

        //Verify Result text is as expected
        Assert.assertTrue(result.equals("You entered: SPACE"), "The result should be: \"You entered: SPACE\"" +
                "\nBut it is actually is: " + result);
    }
}
