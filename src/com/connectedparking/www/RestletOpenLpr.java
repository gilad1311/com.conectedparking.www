package com.connectedparking.www;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RestletOpenLpr {


    private static WebElement element = null;
    private static WebDriver driver = null;
    public static int result = 1;

    @FindBy(how = How.ID, using = Constants.RESTCONTENTTYP)
    private static WebElement restconectedtype;
    @FindBy(how = How.ID, using = Constants.RESTACCEPET)
    private static WebElement restaccept;
    @FindBy(how = How.ID, using = Constants.RESTAUTHOROZATION)
    private static WebElement restauthorozation;
    @FindBy(how = How.ID, using = Constants.RESTBODYSCRIPT)
    private static WebElement restbody;
    @FindBy(how = How.ID, using = Constants.RESTSENDREQ)
    private static WebElement restsend;


    public RestletOpenLpr(WebDriver driver) {// constructor
        this.driver = driver;
    }

    public static void open_Rest() {
        driver.get("chrome-extension://aejoelaoggembcahagimdiliamlcdmfm/restlet_client.html");
    }
}