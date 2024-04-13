package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HorizontalSliderPage extends BasePageObject{
    private final String pageUrl = "https://the-internet.herokuapp.com/horizontal_slider";
    private final By rangeLocator = By.id("range");//То, что отображает значение
    private final By sliderLocator = By.tagName("input");//То, что двигаем

    public HorizontalSliderPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    //Open HorizontalSliderPage with its URL
    public void openPage(){
        log.info("Opening HorizontalSliderPage");
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    //Move slider to specified value
    public void setSliderTo(String value){
        log.info("Move slider to " + value);

        //Move slider normal method
        //Find the xOffset using given value - Тут не работает: программа сразу выставляет значение 5
//        int width = find(sliderLocator).getSize().getWidth();
//        double percent = Double.parseDouble(value) / 5;
//        int xOffset = (int) (width * percent);//То есть мы узначем количество пикселей на которое надо подвинуть слайдер
//
//        Actions action = new Actions(driver);
//        action.dragAndDropBy(find(sliderLocator), xOffset, 0).build().perform();//Если xOffset > 0, подвинет слайдер направо,
        //если xOffset < 0, налево.

        //Workaround method
        //Calculate number of steps
        int steps = (int) (Double.parseDouble(value)/0.5);//Так как у нас значения отличаются на 0.5, соответственно,
        // чтобы узнать сколько их может быть, делим на 0.5

        //Perform steps
        pressKey(sliderLocator, Keys.ENTER);
        for(int i = 0; i < steps; i++){
            pressKey(sliderLocator, Keys.ARROW_RIGHT);
        }

    }

    public String getSliderValue(){
        String value = find(rangeLocator).getText();
        log.info("Slider value is " + value);
        return value;
    }

    }
