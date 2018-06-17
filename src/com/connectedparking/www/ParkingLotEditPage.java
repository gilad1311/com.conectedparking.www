package com.connectedparking.www;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class ParkingLotEditPage {
    private static boolean error=false;
    public static String popuptext;
    private static WebElement element = null;
    private static WebDriver driver = null;
    public static int result = 1;


    @FindBy(how = How.XPATH, using = Constants.GOTOPARKINGLOTLIST)
    private static WebElement gotoparkinglot;
    @FindBy(how = How.ID, using = Constants.PARKINGLOTTOEDIT)
    private static WebElement parkinglottoedit;
    @FindBy(how = How.XPATH, using = Constants.EDITPARKINGLOT)
    private static WebElement editplbutton;
    @FindBy(how = How.XPATH, using = Constants.EDITPARKINGLOTDETAIL)
    private static WebElement editparkinglot;
    @FindBy(how = How.XPATH, using = Constants.LOTTITLE)
    private static WebElement lottitle;
    @FindBy(how = How.XPATH, using = Constants.TOTALCAPACITY)
    private static WebElement totalc;
    @FindBy(how = How.XPATH, using = Constants.MONTHLYCAPACITY)
    private static WebElement monthlyc;
    @FindBy(how = How.XPATH, using = Constants.RESERVEDCAPACITY)
    private static WebElement reservedc;
    @FindBy(how = How.XPATH, using = Constants.TRANSIENTCAPACITY)
    private static WebElement tranc;
    @FindBy(how = How.XPATH, using = Constants.LOTALTERATIONSVE)
    private static WebElement alterationsave;
    @FindBy(how = How.XPATH, using = Constants.ALLOWENTERWHENF)
    private static WebElement allowentrancewfull;
    @FindBy(how = How.XPATH, using = Constants.ALLOWRESERVATIONBUTTON)
    private static WebElement allowreservation;
    @FindBy(how = How.XPATH, using = Constants.ENTRYBEFORE)
    private static WebElement enterbeforeinc;
    @FindBy(how = How.XPATH, using = Constants.ENTRYAFTER)
    private static WebElement enterafterinc;
    @FindBy(how = How.XPATH, using = Constants.ENTRYENRDEC)
    private static WebElement getearlyentrydec;
    @FindBy(how = How.XPATH, using = Constants.ENTRYBEFOREDEC)
    private static WebElement getEnterbeforedec;
    @FindBy(how = How.XPATH, using = Constants.SESSIONTIMER)
    private static WebElement sessiontimmer;



    public ParkingLotEditPage(WebDriver driver) {// constructor
        this.driver = driver;
    }
    public static void click_To_Parking_Lot_Edit() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 300);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.GOTOPARKINGLOTLIST)));
            gotoparkinglot.click();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.sleep(200);
            List<WebElement> facility = driver.findElements(By.xpath(Constants.PARKINGLOTTITLETOCLICK));
            for (WebElement w : facility) {
                //select facility to edit
                if (w.getText().equals("SAP IL H3")) {
                    String facilityl = w.getAttribute("id");
                    wait.until(ExpectedConditions.elementToBeClickable(By.id(facilityl)));
                    driver.findElement(By.id(facilityl)).click();
                    result=0;
                }
            }
            wait.until(ExpectedConditions.elementToBeClickable(editparkinglot));
            editparkinglot.click();
            } catch (NoSuchElementException g) {
            g.printStackTrace();
            result = 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void edit_Capacity() {
        WebDriverWait wait = new WebDriverWait(driver, 300);
        wait.until(ExpectedConditions.elementToBeClickable(lottitle));
        try {
            totalc.clear();
            totalc.sendKeys("20");
            monthlyc.clear();
            monthlyc.sendKeys("12");
            reservedc.clear();
            reservedc.sendKeys("11");
            tranc.clear();
            tranc.sendKeys("12");
            alterationsave.click();
            result = 0;
        } catch (NoSuchElementException g) {
            g.printStackTrace();
        }

       try{
             boolean error = IsVisible(By.xpath("//span [contains(@id,'__text54')]"));
             if (error) {
                 popuptext = driver.findElement(By.id("__mbox0-scrollCont")).getText();
                 driver.findElement(By.id("__mbox-btn-0")).click();
             result = 1;  }

       }catch (NoSuchElementException u) {
           u.printStackTrace();
           result = 0;
       }
    }
    public static void reset_Capacity(String total,String monthly,String reserve,String transit) {
        WebDriverWait wait = new WebDriverWait(driver, 300);
        wait.until(ExpectedConditions.visibilityOfAllElements(lottitle));
        try {
            totalc.clear();
            totalc.sendKeys(total);
            monthlyc.clear();
            monthlyc.sendKeys(monthly);
            reservedc.clear();
            reservedc.sendKeys(reserve);
            tranc.clear();
            tranc.sendKeys(transit);
            alterationsave.click();
            result = 0;
        } catch (NoSuchElementException g) {
            g.printStackTrace();
        }
    }
    public static void edit_Policy() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 300);

            wait.until(ExpectedConditions.elementToBeClickable(editparkinglot));
            editparkinglot.click();
            wait.until(ExpectedConditions.elementToBeClickable(allowentrancewfull));
            allowentrancewfull.click();
            allowreservation.click();
            enterbeforeinc.click();
            enterafterinc.click();
            enterafterinc.click();
            driver.findElement(By.xpath("//input[contains(@id,'--closeSessionHourTimePicker-inner')]")).clear();
            driver.findElement(By.xpath("//input[contains(@id,'--closeSessionHourTimePicker-inner')]")).sendKeys("09:00 AM");
//            sessiontimmer.click();
//            driver.findElement(By.id("__button10-content")).click();
            alterationsave.click();
            result = 0;
        } catch (NoSuchElementException g) {
            g.printStackTrace();
            result=1;
        }
    }
    public static void reset_Policy() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 300);

            wait.until(ExpectedConditions.elementToBeClickable(editparkinglot));
            editparkinglot.click();
            wait.until(ExpectedConditions.elementToBeClickable(allowentrancewfull));
            allowentrancewfull.click();
            allowreservation.click();
            getearlyentrydec.click();
            getEnterbeforedec.click();
            driver.findElement(By.xpath("//input[contains(@id,'--closeSessionHourTimePicker-inner')]")).clear();
            driver.findElement(By.xpath("//input[contains(@id,'--closeSessionHourTimePicker-inner')]")).sendKeys("08:00 AM");
            alterationsave.click();
            result = 0;
        } catch (NoSuchElementException g) {
            g.printStackTrace();
            result=1;
        }
    }
        /**l
         * Returns whether an element is visible
         *
         * @param locator the locator to find the desired element
         * @return true if the element exists and is visible, false otherwise
         */
        public static boolean IsVisible(By locator)
        {
            List<WebElement> elements = driver.findElements(locator);
            if (elements.isEmpty())
            {
                // element doesn't exist
                return false;
            }
            else
            {
                // element exists, check for visibility
                return elements.get(0).isDisplayed();
            }
        }
}
