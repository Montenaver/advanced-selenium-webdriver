package com.herokuapp.theinternet.hoverstests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.HoversPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class HoversTests extends TestUtilities {

    @Test
    public void user2ProfileTest(){
        log.info("Starting user2ProfileTest");

        //Open HoversPage
        HoversPage hoversPage = new HoversPage(driver, log);
        hoversPage.openPage();

        //Open User 2 profile
        hoversPage.openUserProfile(2);

        //Verify correct user profile opened
        Assert.assertTrue(hoversPage.getCurrentUrl().contains("/users/2"),
                "URL of the opened page is not as User 2 page URL");
    }
}
