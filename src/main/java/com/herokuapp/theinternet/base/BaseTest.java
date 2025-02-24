package com.herokuapp.theinternet.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;


@Listeners({ com.herokuapp.theinternet.base.TestListener.class })
public class BaseTest {
    protected WebDriver driver;
    protected Logger log;

    protected String testSuiteName;
    protected String testName;
    protected String testMethodName;


    @Parameters({ "browser", "chromeProfile", "deviceName" })
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, @Optional("chrome") String browser, @Optional String profile, @Optional String deviceName, ITestContext ctx) {//важна
        //последовательность: значения параметров будут присваеваться в этой же последовательности (вначале браузер, потом профиль)
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);

        //Create instance of the BrowserDriverFactory
        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);

        //create driver with profile if exists
        if (profile != null){
            driver = factory.createChromeWithProfile(profile);
        } else if (deviceName != null){
            driver = factory.createChromeWithMobileEmulation(deviceName);
        } else {
            //Create driver using the factory instance
            driver = factory.createDriver();
        }

        driver.manage().window().maximize();

        this.testSuiteName = ctx.getSuite().getName();//Вернет имя набора из .xml файла
        this.testName = testName;
        this.testMethodName = method.getName();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Close driver");
        // Close browser
        driver.quit();
    }
}
