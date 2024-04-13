package com.herokuapp.theinternet.jserrortests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.JSErrorPage;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class JSErrorTests extends TestUtilities {

    @Test
    public void jsErrorTest(){
        log.info("Starting jsErrorTest");
        SoftAssert softAssert = new SoftAssert();

        //Open jsErrorPage
        JSErrorPage jsErrorPage = new JSErrorPage(driver, log);
        jsErrorPage.openPage();

        //Get logs
        List<LogEntry> logs = getBrowserLogs();

        //Verifying there are no JavaScript errors in console
        for (LogEntry logEntry : logs) {//для каждого лога в logs
            if (logEntry.getLevel().toString().equals("SEVERE")) {//если его уровень серьезности соответствует SEVERE
                softAssert.fail("Sever error: " + logEntry.getMessage());
            }
        }
        softAssert.assertAll();
    }
}
