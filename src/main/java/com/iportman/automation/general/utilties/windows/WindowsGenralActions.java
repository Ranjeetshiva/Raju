package com.iportman.automation.general.utilties.windows;

import com.iportman.automation.baseDriver.PageDriver;
import com.iportman.automation.general.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.NoSuchElementException;

public class WindowsGenralActions extends PageDriver {

    Logger logger = LoggerHelper.getLogger(WindowsGenralActions.class);
    WebDriverWait wait =null;
    public final int timeOut = 45;

    public boolean doesElementExists(By element){
        try{
            driver.findElement((By) element);
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public void WaitForElement(WebElement element, long timeOutInSeconds){
        logger.info("waiting for element visibilityOf..");
        wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
        logger.info("element is visible..");
    }

    public <T> boolean enterText(T element,String value){
        WebElement textBox = findElement(element);
        Assert.assertNotNull(textBox);
        textBox.clear();
        textBox.sendKeys(value);
        return true;
    }

    public <T> WebElement findElement(T element){
        if(element.getClass().toString().contains("By")){
            return driver.findElement((By) element);
        }else {
            return this.wait.until(ExpectedConditions.visibilityOf((WebElement) element));
        }
    }

    public <T> boolean clickElement(T element){
        WebElement button = findElement(element);
        Assert.assertNotNull(button);
        button.click();
        return true;
    }


    /**
     * method to click on an element with action class
     * @param element to be clicked
     */
    public void clickOnElementUsingActions(By element){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(element));
        actions.click().perform();
    }


    /**
     * @author ranjeet m
     * method to click on an element using javascript
     * @param element to be clicked
     */
    public boolean clickOnElementUsingJs(By element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement webElement = driver.findElement(element);
        js.executeScript("arguments[0].click();", webElement);
        return true;
    }

    /**
     * @author ranjeet m
     * method to get title of current webpage
     * @return String name of a webpage
     */
    public String getTitle(){
        return driver.getTitle();
    }

    /**
     * @author ranjeet m
     * String fromDate = "31-07-2021 17:55"
     * selectDate(departureFrom,fromDate)
     * @param element
     * @param dateVal
     */
    public static void enterDate(WebElement element, String dateVal){
        element.clear();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('value','"+dateVal+"');",element);
    }

    /**
     * @author ranjeet m
     * method to click on ok of alert popup,
     * exception is thrown if no alert is present
     */
    public void acceptAlert(){
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e){
            throw new NoAlertPresentException();
        }
    }

    /**
     * @author ranjeet m
     * method to get text message from alert
     * @return message text which is displayed
     */
    public String getAlertText()
    {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            return alertText;
        } catch (NoAlertPresentException e){
            throw new NoAlertPresentException();
        }
    }

    /**
     * @author ranjeet m
     * method to verify if alert is present
     * @return returns true if alert is present else false
     */
    public boolean isAlertPresent()
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException e) {
            throw new NoAlertPresentException();
        }
    }

    /**
     * @author ranjeet m
     * method to select a value from dropdown with index
     * @param selectLocator element with select tag
     * @param valueToBeSelectedindex index to be selected
     */
    public void selectValuefromDropdownviaIndex(By selectLocator, int valueToBeSelectedindex){
        Select  selectFromDropdown = new Select(findElement(selectLocator));
        selectFromDropdown.selectByIndex(valueToBeSelectedindex);

    }

    /**
     * @author ranjeet m
     * works if existing page
     * method to wait until page is loaded completely
     * @param PageName String name of current webpage
     */
    public boolean waitForPageToLoadJs(String PageName) {
        String pageLoadStatus;
        JavascriptExecutor js;
        do {
            js = (JavascriptExecutor) driver;
            pageLoadStatus = (String)js.executeScript("return document.readyState");
            logger.info(".");
        } while ( !pageLoadStatus.equalsIgnoreCase("complete") );
        logger.info(PageName + " page loaded successfully");
        return true;
    }
}
