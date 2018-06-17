package com.connectedparking.www;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class FacilitiesPage {
    private static WebElement element = null;
    private static WebDriver driver = null;
    public static int result = 1;

    @FindBy(how = How.ID, using = Constants.FACILITIESPAGENAV)
    private static WebElement facilitynav;
    @FindBy(how = How.XPATH, using = Constants.FACILITITOEDIT)
    private static WebElement facilitytoedit;
    @FindBy(how = How.ID, using = Constants.EDITBUTTON)
    private static WebElement editbutton;
    @FindBy(how = How.XPATH, using = Constants.GENERALINFOFN)
    private static WebElement generalfacilityname;
    @FindBy(how = How.ID, using = Constants.GENERALINFOTIMEZONE)
    private static WebElement generaltimezone;
    @FindBy(how = How.ID, using = Constants.GENERALINFOLANG)
    private static WebElement generalinfolanguage;
    @FindBy(how = How.ID, using = Constants.ADDFACILITYMANAGE)
    private static WebElement addfacilitymanager;
    @FindBy(how = How.ID, using = Constants.DELETEFACILITYMANAGER)
    private static WebElement deletfacilitymanager;
    @FindBy(how = How.ID, using = Constants.SAVEFACILITYINFO)
    private static WebElement facilityinfosave;
    @FindBy(how = How.XPATH, using = Constants.GENERALFACILITYLANGARROW)
    private static WebElement generalinfolangarrow;


    public FacilitiesPage(WebDriver driver) {// constructor
        this.driver = driver;
    }

    public static void click_To_Facilities_Page() {
        WebDriverWait wait = new WebDriverWait(driver, 200);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(Constants.FACILITIESPAGENAV)));
        facilitynav.click();
    }

    public static void go_To_Edit_Facility(String facility) {
        try {
            Thread.sleep(300);
            List<WebElement> f = driver.findElements(By.xpath(Constants.FACILITITOEDIT));
            for (WebElement w : f) {
                //select facility to edit
                if (w.getText().equals(facility)) {
                    String facilityl = w.getAttribute("id");
                    WebDriverWait wait = new WebDriverWait(driver, 500);
                    wait.until(ExpectedConditions.elementToBeClickable(By.id(facilityl)));
                    driver.findElement(By.id(facilityl)).click();
                    Thread.sleep(100);
                    result = 0;
                    break; //English (United States)
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            result = 1;
        } catch (NoSuchElementException g) {
            g.printStackTrace();
            result = 1;
        }
    }

    public static void go_Edit_Updated_Facility(String facility) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 300);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.FACILITITOEDIT)));
            List<WebElement> f = driver.findElements(By.xpath(Constants.FACILITITOEDIT));
            for (WebElement w : f) {
                //select facility to edit
                if (w.getText().equals(facility)) {
                    String facilityl = w.getAttribute("id");

                    wait.until(ExpectedConditions.elementToBeClickable(By.id(facilityl)));
                    driver.findElement(By.id(facilityl)).click();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    result = 0;
                    break; //English (United States)
                }
            }
        } catch (NoSuchElementException g) {
            g.printStackTrace();
            result = 1;
        }
    }

    public static void edit_Facility() {
        WebDriverWait wait = new WebDriverWait(driver, 300);
        wait.until(ExpectedConditions.elementToBeClickable(editbutton));
        editbutton.click();

    }

    public static void edit_General_Info(String facility) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 300);

            wait.until(ExpectedConditions.elementToBeClickable(generalfacilityname));
            generalfacilityname.clear();
            generalfacilityname.sendKeys(facility);
            driver.findElement(By.id("__jsview4--facilityEdit_cbTimeZone-arrow")).click();
            List<WebElement> timezone = driver.findElements(By.xpath(Constants.GENERALINFOTIMEZONE));
            for (WebElement time : timezone) {
                if (time.getText().equals("-07:00 America/Los_Angeles (Pacific Standard Time)")) {
                    time.click();
                    result = 0;
                    break;
                }
            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            generalinfolangarrow.click();
            List<WebElement> lang = driver.findElements(By.xpath(Constants.GENERALINFOLANG));
            for (WebElement l : lang) {
                if (l.getText().equals("German (Germany)")) {
                    l.click();
                    result = 0;
                    break; //English (United States)
                }
            }
            facilityinfosave.click();
        } catch (NoSuchElementException g) {
            g.printStackTrace();
            result = 1;
        }

    }
    public static void reset_General_Info(String origFacility) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 300);

            wait.until(ExpectedConditions.elementToBeClickable(generalfacilityname));
            generalfacilityname.clear();
            generalfacilityname.sendKeys(origFacility);
            driver.findElement(By.id("__jsview4--facilityEdit_cbTimeZone-arrow")).click();
            List<WebElement> timezone = driver.findElements(By.xpath(Constants.GENERALINFOTIMEZONE));
            for (WebElement time : timezone) {
                if (time.getText().equals("+03:00 Asia/Jerusalem (Israel Standard Time)")) {//-07:00 America/Los_Angeles (Pacific Standard Time)
                    time.click();
                    result = 0;
                    break;
                }
            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            generalinfolangarrow.click();
            List<WebElement> lang = driver.findElements(By.xpath(Constants.GENERALINFOLANG));
            for (WebElement l : lang) {
                if (l.getText().equals("English (United States)")) {
                    l.click();
                    result = 0;
                    break; //English (United States)
                }
            }
            facilityinfosave.click();
        } catch (NoSuchElementException g) {
            g.printStackTrace();
            result = 1;
        }

    }
        public static void add_Facility_Managers_List () {
            addfacilitymanager.click();
        }
    public static void assign_Facility_Managers () {
        // pick from manager list id-__jsview4--facilityEdit_tabFacilityManagers-listUl
      //assign tick id-  __box0-__jsview4--facilityEdit_tabFacilityManagers-3-CbBg
    }
    public static void delete_Facility_Managers () {
        deletfacilitymanager.click();
    }

        }

