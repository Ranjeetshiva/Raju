package com.iportman.automation.general.utilties;
import com.iportman.automation.baseDriver.PageDriver;
import com.iportman.automation.general.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilties extends PageDriver {
    static Logger logger = LoggerHelper.getLogger(Utilties.class);
    private WebElement element = null;

    public static String getConfigProperty(String key){
        Properties prop = new Properties();
        String configFile =  "src" + File.separator + "test" + File.separator + "resources" + File.separator +"config.properties";

        logger.info("Config File Location "+configFile);
        try{
            FileInputStream fis = new FileInputStream(configFile);
             prop.load(fis);
            }
        catch (IOException e){
        e.printStackTrace();
        }
        return prop.getProperty(key);
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
     * Method to verify page title
     *
     * @param title    : String : expected title
     * @param testCase : Boolean : test case [true or false]
     */
    public void checkTitle(String title, boolean testCase)  {
        String pageTitle = getTitle();

        if (testCase) {
            if (!pageTitle.equals(title))
               logger.info("Page Title Not Matched, Actual Page Title : " + pageTitle);
        } else {
            if (pageTitle.equals(title))
                logger.info("Page Title Matched, Actual Page Title : " + pageTitle);
        }
    }
}
