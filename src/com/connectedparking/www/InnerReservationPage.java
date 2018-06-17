package com.connectedparking.www;

import com.connectedparking.www.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.connectedparking.www.ReservationDetailsPage.IsVisible;

public class InnerReservationPage {
    private static WebElement element = null;
    private static WebDriver driver = null;
    public static int result = 1;
    private static String reservationtime;
    public static String popuptext;
    private static String localdate;
    private static String rfn;
    private static long time;
    public static String timename;
    public static String searchname;
    private static String creditsixn;


    // using @findBy annotaion to find element
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_TITLE)
    private static WebElement addreservationtitle;
    @FindBy(how = How.ID, using = Constants.ADD_RESERVATION_BUTTON)
    private static WebElement reserveationbutton;
    @FindBy(how = How.ID, using = Constants.ADD_RESERVATION_CANCEL)
    private static WebElement reserveationcancelbutton;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_VEHICLE_INFORMATION)
    private static WebElement vehicleinformation;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_TO_DATE_PICKER)
    private static WebElement todatepicker;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_SAVE)
    private static WebElement savebutton;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_REGION)
    private static WebElement region;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_PHONE)
    private static WebElement reservationphone;
    @FindBy(how = How.ID, using = Constants.ADD_RESERVATION_PARKING_LOT_ID)
    private static WebElement parkinglot;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_LICENSE_PLATE)
    private static WebElement licenseplate;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_LAST_NAME)
    private static WebElement reservlastname;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_FROM_DATE_PICKER)
    private static WebElement fromdatepicker;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_FIRST_NAME)
    private static WebElement reservfirstname;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_FACILITY_LIST)
    private static WebElement facilitylist;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_FACILITY)
    private static WebElement svnfacility;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_EMAIL_QR_CODE)
    private static WebElement sendqrcode;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_EMAIL)
    private static WebElement reservemail;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_COUNTRY)
    private static WebElement reservationcountry;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_CC_LAST_FOURE)
    private static WebElement ccexpretionlastfour;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_CC_FIRSTSIX)
    private static WebElement ccexpretionfirstsix;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_CC_EXPIRATIONYEAR)
    private static WebElement ccexpretionyear;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_CC_EXPIRATIONMONTH)
    private static WebElement ccexpretionmonth;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVERATION_CURRENT_MIN)
    private static WebElement fromdatemin;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVEATION_END_HOUR)
    private static WebElement todatehour;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVERATION_OKTHEDATE)
    private static WebElement dateok;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_TODATEOK)
    private static WebElement todateok;
    @FindBy(how = How.XPATH, using = Constants.EXPECTEDERRORTEXT)
    private static WebElement expctederror;


    public InnerReservationPage(WebDriver driver) {// constructor
        this.driver = driver;
    }


    public static void is_Add_Reservation_Page() {

        // Create object of SimpleDateFormat class and decide the format

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
        Date date = new Date();
        localdate = dateFormat.format(date);
        time = (System.currentTimeMillis() / 10000);
        timename = String.valueOf(time / 850);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.ADD_RESERVATION_TITLE)));
        if (addreservationtitle.getText().equals("Add Reservation")) {
            result = 0;

        } else if (addreservationtitle.getText() != "Add Reservation") {
            result = 1;
            System.out.println("There was a problem in Add Reservation page button, or the lending page is not Add Reservation");
        }
    }

    public static void pick_Lot_A_Facility( String facility,String lot){
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//div//span[contains(@id,'-facilitiesSelect-arrow')]")).click();
        GeneralCode.selectFromList(Constants.ADD_RESERVATION_FACILITY_LIST,facility);
        driver.findElement(By.xpath("//div//span[contains(@id,'parkingLotsSelect-arrow')]")).click();
        GeneralCode.selectFromList(Constants.ADD_RESERVATION_FACILITY,lot);
    }

    public static void pick_From_Date() {
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(driver, 300);
        wait.until(ExpectedConditions.elementToBeClickable(fromdatepicker));
        fromdatepicker.click();
        wait.until(ExpectedConditions.elementToBeClickable(fromdatemin));
        fromdatemin.click();
        try {
            Thread.sleep(250);
            fromdatemin.sendKeys(Keys.PAGE_DOWN);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(300);
            fromdatemin.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(250);
            dateok.click();
            result = 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void pick_To_Date() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(todatepicker));
        todatepicker.click();
        wait.until(ExpectedConditions.elementToBeClickable(todatehour));
        todatehour.click();
//        try {
//            Thread.sleep(200);
//            todatehour.sendKeys(Keys.DOWN);
//
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            Thread.sleep(200);
            todateok.click();
            result = 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void set_Personal_Info() {

        if (timename == null) {
            timename = System.currentTimeMillis() / 1000 + "o";
        }
        reservfirstname.sendKeys("Auto");
        rfn = reservfirstname.getText();
        reservlastname.sendKeys(timename);
        reservemail.sendKeys(Constants.SENDTOMAIL);
        reservationphone.sendKeys(Constants.PHONENUMBER);
        searchname = "A" + time / 850 + "O";
        creditsixn = String.valueOf(System.currentTimeMillis() / 1000);
        licenseplate.sendKeys(searchname);
        driver.findElement(By.xpath(Constants.ADD_RESERVATION_COUNTRY)).click();
        GeneralCode.selectFromList("//li[contains(@class,'sapMSelectListItem')]", "Israel");
        ccexpretionfirstsix.sendKeys("666666");
        ccexpretionlastfour.sendKeys("4444");
        driver.findElement(By.xpath(Constants.ADD_RESERVATION_CC_EXPIRATIONMONTH)).click();
        try {
            Thread.sleep(20);
            GeneralCode.selectFromList(Constants.CCMONTHLIST, "10");
            Thread.sleep(20);
            ccexpretionyear.click();
            Thread.sleep(20);
            GeneralCode.selectFromList(Constants.CCMONTHLIST, "2020");
            Thread.sleep(20);
            sendqrcode.click();
            driver.findElement(By.xpath("//div[contains(@id,'-cbSendQR-CbBg')]")).click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void save_Reservation() {
        savebutton.click();
        result = 0;

    }
    public static void expected_Error_After_Save(){
        try {
            savebutton.click();
            result=0;
           popuptext = expctederror.getText();
           driver.findElement(By.xpath("//button[contains(@id,'__mbox-btn')]")).click();
        } catch (NoSuchElementException a) {
            result = 1;
        }
    }

}
