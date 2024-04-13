package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BasePageObject {
    protected WebDriver driver;
    protected Logger log;
    public BasePageObject(WebDriver driver, Logger log){
        this.driver = driver;
        this.log = log;
    }

    //Open page with given URL
    protected void openUrl(String url){
        driver.get(url);
    }

    //Find element using given locator
    protected WebElement find(By locator){
        return driver.findElement(locator);
    }

    //Find all elements using given locator
    protected List<WebElement> findAll(By locator){
        return driver.findElements(locator);
    }

    //Click on element with given locator when its visible
    protected void click(By locator){
        waitForVisibilityOf(locator, Duration.ofSeconds(5));
        find(locator).click();
    }

    //Type given text into element with given locator
    protected void type(String text, By locator){
        waitForVisibilityOf(locator, Duration.ofSeconds(5));
        find(locator).sendKeys(text);
    }
    //Get current URL from browser.
    // Метод public т.к. мы будем использовать его в тестах, а не только в Page Object-ах
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    //Get title of the current page
    public String getCurrentPageTitle(){
        return driver.getTitle();
    }

    //Get source of current page
    public String getCurrentPageSource(){
        return driver.getPageSource();//Возвращает html страницы
    }


    //Wait for specific ExpectedCondition for given amount of time in seconds
    private void waitFor(ExpectedCondition<WebElement> condition, Duration timeOut){
         timeOut = timeOut != null ? timeOut : Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(condition);
    }


    //Wait for given number of seconds for element with given locator to be visible on the page
    protected void waitForVisibilityOf(By locator, Duration... timeOut){//... = optional
        int attempts = 0;
        while (attempts < 2){
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOut.length > 0 ? timeOut[0] : null));
                break;
            } catch (StaleElementReferenceException e){}
            attempts++;
        }
    }

    //wait for alert present and then switch to it
    protected Alert switchToAlert(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }

    protected void switchToWindowWithTitle(String expectedTitle){
        //Switch to new window
        String firstWindow = driver.getWindowHandle();//определяе текущее окно или вкладку

        Set<String> allWindows = driver.getWindowHandles();//возвращает набор identifiers открытых окон
        Iterator<String> windowsIterator = allWindows.iterator();

        while (windowsIterator.hasNext()){//итератор проходит по всем доступных открытым окнам, извлекая их windowHandle
            //identifier-ы и если =>
            String windowHandle = windowsIterator.next().toString();
            if (!windowHandle.equals(firstWindow)){//idntifier проверяемой страницы не такой же как у текущей странице
                driver.switchTo().window(windowHandle);//то мы переключимся на проверяемое окно, по ее windowHandle
                if (getCurrentPageTitle().equals(expectedTitle)){//и, переклюившись на нее, получим Заголовок этой
                    //новой страницы, и если он соответствует заданного (см. переданный параметр), метод будет прерван
                    //так как мы нашли нужную страницу
                    break;
                }
            }
        }
    }

    //Switch to iFrame using its locator
    protected void switchToFrame(By frameLocator){
        log.info("Switching to a new frame");
        driver.switchTo().frame(find(frameLocator));
    }

    //Press Key on locator
    protected void pressKey(By locator, Keys key){
        find(locator).sendKeys(key);//Тот же sendKeys, что используем для ввода текста в поле
    }

    //Press Key using Action class
    public void pressKeyWithActions(Keys key){
        log.info("Pressing " + key.name() + " using Action class");
        Actions action = new Actions(driver);
        action.sendKeys(key).build().perform();
    }

    //Perform scroll to the bottom
    public void scrollToBottom(){
        log.info("Scrolling to the bottom of the page");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    //Drag 'from' element to 'to' element
    protected void performDragAndDrop(By from, By to){
//        Actions action = new Actions(driver);
//        action.dragAndDrop(find(from), find(to)).build().perform();

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(
                "function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                        + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n"
                        + "data: {},\n" + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                        + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n"
                        + "return event;\n" + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                        + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                        + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                        + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n"
                        + "}\n" + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                        + "var dragStartEvent =createEvent('dragstart');\n"
                        + "dispatchEvent(element, dragStartEvent);\n" + "var dropEvent = createEvent('drop');\n"
                        + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                        + "var dragEndEvent = createEvent('dragend');\n"
                        + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                        + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                        + "simulateHTML5DragAndDrop(source,destination);",
                find(from), find(to));
    }

    //Perform mouse hover over element
    protected void hoverOverElement(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    //Add cookie
    public void setCookie(Cookie ck){//В параметре указываем cookie, который хотим добавить
        log.info("Adding cookie " + ck.getName());
        driver.manage().addCookie(ck);
        log.info("Cookie added");
    }

    //Get cookie value using cookie name
    public String getCookie(String name){
        log.info("Getting value of cookie " + name);
        return driver.manage().getCookieNamed(name).getValue();
    }

}
