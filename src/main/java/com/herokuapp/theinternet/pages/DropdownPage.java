package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePageObject{

    private By dropdown = By.xpath("//select[@id='dropdown']");

    public DropdownPage(WebDriver driver, Logger log){
        super(driver, log);
    }

    //This method selects given option from dropdown
    public void selectOption(int i){
        log.info("Selecting option " + i + " from dropdown");
        WebElement dropdownElement = find(dropdown);
        Select dropdown = new Select(dropdownElement);

        //There are 3 ways to use Select class
        //#1
//        dropdown.selectByIndex(i);

        //#2 Используем его
        dropdown.selectByValue("" + i);//Этот метод требует ввести String, поэтому пришлось передать пустую String

        //#3
//        dropdown.selectByVisibleText("Option " + i);
    }

    //This method returns selected option in dropdown as a String
    public String getSelectedOption(){
        WebElement dropdownElement = find(dropdown);
        Select dropdown = new Select(dropdownElement);
        String selectedOption = dropdown.getFirstSelectedOption().getText();
        log.info(selectedOption + " is selected in dropdown");
        return selectedOption;
    }

}
