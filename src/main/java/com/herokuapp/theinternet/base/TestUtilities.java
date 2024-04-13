package com.herokuapp.theinternet.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.DataProvider;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestUtilities extends BaseTest{

    //static sleep
    protected void sleep(long millis){
        try{
            Thread.sleep(millis);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @DataProvider(name="files")
    protected static Object[][] files(){
        return new Object[][] {
                {1,"GET_faq_504.txt"},
                {2,"MT5.png"},
                {3,"TMX_171.jpg"}
        };
    }

    //Take screenshot
    protected void takeScreenshot(String fileName){
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")//Ищет, где проект находится на нашем компе
                + File.separator + "test-output"
                + File.separator + "screenshots"
                + File.separator + getTodayDate()
                + File.separator + testSuiteName
                + File.separator + testName
                + File.separator + testMethodName
                + File.separator + getSystemTime()
                + " " + fileName + ".png";
        try{
            FileUtils.copyFile(scrFile, new File(path));//для FileUtils нужна новая dependency!!!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Today date in yyyyMMdd format
    private  static String getTodayDate(){
        return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    //Current time in HHmmssSS format
    public String getSystemTime(){
        return (new SimpleDateFormat("HHmmssSS").format(new Date()));
    }

    //Get logs from browser console
    protected List<LogEntry> getBrowserLogs(){
        LogEntries log = driver.manage().logs().get("browser");//То есть просто достаем логи из браузера
        List<LogEntry> logList = log.getAll();
        return logList;
    }

}
