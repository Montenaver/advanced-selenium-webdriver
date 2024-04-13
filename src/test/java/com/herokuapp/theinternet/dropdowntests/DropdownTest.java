package com.herokuapp.theinternet.dropdowntests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.DropdownPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DropdownTest extends TestUtilities {

    @Parameters({ "optionToSelect"})
    @Test
    public void optionTwoTest(String optionToSelect){
        log.info("Starting optionTwoTest");

        //open main page and click on Dropdown link
        WelcomePage welcomePage = new WelcomePage(driver, log);
        DropdownPage dropdownPage = welcomePage.openPage().clickDropdownLink();

        //Select Option 2
        dropdownPage.selectOption(2);

        //Verify Option 2 is selected
        String selectedOption = dropdownPage.getSelectedOption();
        Assert.assertTrue(selectedOption.equals(optionToSelect), "Oops! The selected message is wrong." +
                "\nIt is: " + selectedOption + "\nBut it should be: " + optionToSelect);

    }
}
