package com.herokuapp.theinternet.horizontalslidertests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.HorizontalSliderPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HorizontalSliderTests extends TestUtilities {
    @Test
    public void sliderTest(){
        log.info("Starting sliderTest");

        //Open HorizontalSliderPage
        HorizontalSliderPage horizontalSliderPage = new HorizontalSliderPage(driver, log);
        horizontalSliderPage.openPage();

        String value = "3.5";

        //Set slider value
        horizontalSliderPage.setSliderTo(value);

        //Verify slider value
        String sliderValue = horizontalSliderPage.getSliderValue();
        Assert.assertTrue(sliderValue.equals(value), "Range is not correct. It should be " + value + ", but it is " + sliderValue);

    }
}
