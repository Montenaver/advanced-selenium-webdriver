package com.herokuapp.theinternet.alerttests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.JavaScriptAlertPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class jsAlertTest extends TestUtilities {

    @Test
    public void jsAlertTest() {
//        log.info("Starting jsAlertTest");

        SoftAssert softAssert = new SoftAssert();

        //open main page and click on JavaScript Alert Link
        WelcomePage welcomePage = new WelcomePage(driver, log);
        JavaScriptAlertPage alertsPage = welcomePage.openPage().clickJavaScriptAlertLink();

        //click JS Alert button
        alertsPage.openJSAlert();

        //Get alert text
        String alertMessage = alertsPage.getAlertText();

        //click OK button
        alertsPage.acceptAlert();

        //get result text
        String result = alertsPage.getResultText();

        //verifications
        //1 - Alert text is expected
        softAssert.assertTrue(alertMessage.equals("I am a JS Alert"),
                "Alert message is not correct.\nShould be 'I am a JS Alert', it is " + alertMessage);

        //2 - Result text is expected
        softAssert.assertTrue(result.equals("You successfully clicked an alert"),
                "Result text is incorrect.\nIt should be 'You successfully clicked an alert', but it is " +
                        result);
        softAssert.assertAll();
    }

    @Test
    public void jsDismissTest() {
//        log.info("Starting jsDismissTest");

        SoftAssert softAssert = new SoftAssert();

        //open main page and click on JavaScript Alert Link
        WelcomePage welcomePage = new WelcomePage(driver, log);
        JavaScriptAlertPage alertsPage = welcomePage.openPage().clickJavaScriptAlertLink();

        //click JS Confirm button
        alertsPage.openJSConfirm();

        //Get alert text
        String alertMessage = alertsPage.getAlertText();

        //click OK button
        alertsPage.dismissAlert();

        //get result text
        String result = alertsPage.getResultText();

        //verifications
        //1 - Alert text is expected
        softAssert.assertTrue(alertMessage.equals("I am a JS Confirm"),
                "Alert message is not correct.\nShould be 'I am a JS Confirm', it is " + alertMessage);

        //2 - Result text is expected
        softAssert.assertTrue(result.equals("You clicked: Cancel"),
                "Result text is incorrect.\nIt should be 'You clicked: Cancel', but it is " + result);
        softAssert.assertAll();
    }

    @Test
    public void jsAcceptTest() {
//        log.info("Starting jsAcceptTest");

        SoftAssert softAssert = new SoftAssert();


        //open main page and click on JavaScript Alert Link
        WelcomePage welcomePage = new WelcomePage(driver, log);
        JavaScriptAlertPage alertsPage = welcomePage.openPage().clickJavaScriptAlertLink();

        //click JS Confirm button
        alertsPage.openJSConfirm();

        //Get alert text
        String alertMessage = alertsPage.getAlertText();

        //click OK button
        alertsPage.acceptAlert();

        //get result text
        String result = alertsPage.getResultText();

        //verifications
        //1 - Alert text is expected
        softAssert.assertTrue(alertMessage.equals("I am a JS Confirm"),
                "Alert message is not correct.\nShould be 'I am a JS Confirm', it is " + alertMessage);

        //2 - Result text is expected
        softAssert.assertTrue(result.equals("You clicked: Ok"),
                "Result text is incorrect.\nIt should be 'You clicked: Ok', but it is " + result);
        softAssert.assertAll();
    }

    @Test
    public void jsPromptTest() {
        String text = "Hello Alert! I'm not Dmitry, but you should accept me!";
//        log.info("Starting jsPromptTest");

        SoftAssert softAssert = new SoftAssert();

        //open main page and click on JavaScript Alert Link
        WelcomePage welcomePage = new WelcomePage(driver, log);
        JavaScriptAlertPage alertsPage = welcomePage.openPage().clickJavaScriptAlertLink();

        //click JS Prompt button
        alertsPage.openJSPrompt();

        //Get alert text
        String alertMessage = alertsPage.getAlertText() + "[FAIL]";

        //Type text into alert and click OK button
        alertsPage.typeTextIntoAlert(text);

        //Get result text
        String result = alertsPage.getResultText() + "[FAIL]";

        //verifications
        //1 - Alert text is expected
        softAssert.assertTrue(alertMessage.equals("I am a JS prompt"),
                "Alert message is not correct.\nShould be 'I am a JS prompt', it is " + alertMessage);

        //2 - Result text is expected
        softAssert.assertTrue(result.equals("You entered: " + text),
                "Result text is incorrect.\nIt should be 'You entered: " + text + "', but it is " + result);
        softAssert.assertAll();
    }
}
