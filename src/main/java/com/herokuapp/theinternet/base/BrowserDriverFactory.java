package com.herokuapp.theinternet.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BrowserDriverFactory {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;
    private Logger log;

    //create a constructor
    public BrowserDriverFactory(String browser, Logger log){
        this.browser = browser.toLowerCase();
        this.log = log;
    }

    public WebDriver createDriver(){
        //create driver
        log.info("Create diver: " + browser);

        switch(browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver/mac/chromedriver");
                System.setProperty("webdriver.http.factory", "jdk-http-client");//надо было добавить
                //org.seleniumhq.selenium dependency в pom.xml и прописать эту строчку при создании driver-а

                driver.set(new ChromeDriver());
                break;

            case "firefox":
//                System.setProperty("webdriver.gecko.driver", "drivers/geckodriver/linux64/geckodriver-v0.32.2-linux-aarch64/geckodriver");
                System.setProperty("webdriver.gecko.driver", "drivers/geckodriver/linux64/geckodriver-v0.32.2-linux64/geckodriver");
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary("/usr/bin/firefox");
                FirefoxDriver drv = new FirefoxDriver(options);
                driver.set(drv);
                System.out.println("driver is created");
                break;
//                FirefoxOptions options = new FirefoxOptions();
//                options.setBinary("usr/bin/firefox");
//                FirefoxDriver drv = new FirefoxDriver(options);
//                driver.set(drv);
//                System.out.println("driver is created");
//                break;

            case "edge":
                System.setProperty("webdriver.edge.driver", "drivers/msedgedriver/linux64/104.0.1293.70/msedgedriver");
                driver.set(new EdgeDriver());
                break;

            case "chromeheadless":
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver/linux64/111.0.5563.64/chromedriver");
                System.setProperty("webdriver.http.factory", "jdk-http-client");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver.set(new ChromeDriver(chromeOptions));
                break;

            case "frefoxheadless":
                System.setProperty("webdriver.gecko.driver", "drivers/geckodriver/linux64/geckodriver-v0.32.2-linux64/geckodriver");
                FirefoxBinary firefoxBinary = new FirefoxBinary(new File("/usr/bin/firefox"));
                firefoxBinary.addCommandLineOptions("--headless");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary(firefoxBinary);
                driver.set(new FirefoxDriver(firefoxOptions));
                break;

            case "phantomjs":
                System.setProperty("phantomjs.binary.path", "drivers/phantomjs/phantomjs");
                driver.set(new PhantomJSDriver());//Видимо устарел, не запускается
                break;

            case "htmlunit":
                driver.set(new HtmlUnitDriver());//Не надо setProperty() т.к. у нас нет исполняемого файла
                break;

            default:
                System.out.println("Do not know how to start: " + browser + ", starting chrome instead");
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver/linux64/chromedriverlinux64/chromedriver");
                driver.set(new ChromeDriver());
                break;
        }
        return driver.get();
    }

    public WebDriver createChromeWithProfile(String profile){
        log.info("Starting chrome driver with profile: " + profile);//В нашем случае это будет ChromeProfile
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-dir=src/main/resources/Profiles/" + profile);//а это типа путь до ChromeProfile

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver/linux64/111.0.5563.64/chromedriver");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver.set(new ChromeDriver(chromeOptions));
        return driver.get();
    }

    public WebDriver createChromeWithMobileEmulation(String deviceName){
        log.info("Starting driver with " + deviceName + " emulation]");
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver/linux64/111.0.5563.64/chromedriver");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver.set(new ChromeDriver(chromeOptions));
        return driver.get();
    }
}
