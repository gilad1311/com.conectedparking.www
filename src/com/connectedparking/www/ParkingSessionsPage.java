package com.connectedparking.www;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.NoSuchElementException;

public class ParkingSessionsPage {
    private static WebElement element = null;
    public static int result = 1;
    private static WebDriver driver;
    public static String popuptext="";


    // using @findBy annotaion to find element
    @FindBy(how = How.XPATH, using = Constants.EVENTSTATUS)
    private static WebElement eventstatus;
    @FindBy(how = How.XPATH, using = Constants.PICEVENTSTATUS)
    private static WebElement pickedeventstatus;
    @FindBy(how = How.ID, using = Constants.PARKINGSESSIONPAGENAV)
    private static WebElement parkingsessionnav;
    @FindBy(how = How.XPATH, using = Constants.PARKINGSESSIONSEARCH)
    private static WebElement parkingsessionsearch;
    @FindBy(how = How.XPATH, using = Constants.CLOSESESSIONLINK)
    private static WebElement sessionlink;
    @FindBy(how = How.XPATH, using = Constants.CLOSEPARKINGSESSION)
    private static WebElement closeparkingsession;
    @FindBy(how = How.XPATH, using = Constants.FROMSESSIONDATE)
    private static WebElement fromsessiondatetime;
    @FindBy(how = How.XPATH, using = Constants.TOSESSIONDATE)
    private static WebElement tosessiondatetime;
    @FindBy(how = How.XPATH, using = Constants.PREVMONTHDATE)
    private static WebElement prevmonth;
    @FindBy(how = How.XPATH, using = Constants.PREVMONTHTODATE)
    private static WebElement toprevmonth;
    @FindBy(how = How.XPATH, using = Constants.YEARPICDATE)
    private static WebElement picyear;
    @FindBy(how = How.XPATH, using = Constants.CHANGEYEAR)
    private static WebElement changeyear;
    @FindBy(how = How.XPATH, using = Constants.FROMDATEOK)
    private static WebElement fromdateok;


    public ParkingSessionsPage(WebDriver driver) {// constructor
        this.driver = driver;
    }


    public static void click_To_Parking_Sessions() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parkingsessionnav.click();
    }

    public static void search_And_Verify_Session(String sessionstate, String Status, String value) {

        if (value == "") {
            value = InnerReservationPage.searchname;
        }
        if (sessionstate == "") {
            sessionstate = "Open";
        }
        try {
            WebDriverWait wait = new WebDriverWait(driver, 250);
            wait.until(ExpectedConditions.elementToBeClickable(eventstatus));
            eventstatus.click();
            List<WebElement> options = driver.findElements(By.xpath(Constants.PICEVENTSTATUS));
            for (WebElement opt : options) {
                if (opt.getText().equals(sessionstate)) {
                    opt.click();
                    result = 0;
                }
            }
            WebDriverWait waits = new WebDriverWait(driver, 100);
            waits.until(ExpectedConditions.elementToBeClickable(parkingsessionsearch));
            parkingsessionsearch.click();
            waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.PARKINGSESSIONSEARCH)));
            parkingsessionsearch.sendKeys(value);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement stat = driver.findElement(By.xpath("//tr//td[contains (@id,'-transTableRow-__')]"));
            String verify = stat.getText();
            if (verify.equals(Status)) {
                String id = stat.getAttribute("id");
                result = 0;

            } else {
                result = 1;
            }

        } catch (NoSuchElementException a) {
            a.printStackTrace();

        }


    }

    public static void Close_Session() {
        try {
            Thread.sleep(1000);
//           WebElement sessionl= driver.findElement(By.xpath("//span [contains (@data-sap-ui-icon-content,'\uE1F2') and contains (@class,'sapMBtnCustomIcon') and contains (@class,'sapMBtnIcon') and contains (@class,'sapMBtnIconLeft') and contains (@class,'sapUiIcon')and contains (@class, 'sapUiIconMirrorInRTL')]"));
            Thread.sleep(1000);
            sessionlink.click();
            Thread.sleep(1000);
//            WebElement close=  driver.findElement(By.xpath("//span [contains (@data-sap-ui-icon-content,'\uE1C7') and contains (@class,'sapMBtnCustomIcon') and contains (@class,'sapMBtnIcon')and contains (@class,'sapMBtnIconLeft')and contains (@class,'sapUiIcon')and contains (@class,'sapUiIconMirrorInRTL')]"));
            Thread.sleep(1000);
            closeparkingsession.click();
            driver.findElement(By.id("closeSessionDialogFragment--descriptionBox-inner")).sendKeys("closed by automation test");
            List<WebElement> closes = driver.findElements(By.xpath("//button//span[contains (@class,'sapMBtnDefault') and contains (@class,'sapMBtnHoverable')and contains (@class,'sapMBtnInner')and contains (@class,'sapMBtnText') and contains (@class,'sapMFocusable')]"));
            closes.get(0).click();
            result = 0;
        } catch (NoSuchElementException f) {
            System.out.println("Can not close the session");
            result = 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            result = 1;
        }
    }

    public static void pick_From_Date(String lessThenYear) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(driver, 200);
        wait.until(ExpectedConditions.visibilityOfAllElements(fromsessiondatetime));
        fromsessiondatetime.click();
        if (lessThenYear=="y"){
            driver.findElement(By.xpath("//button[contains(@id,'__jsview1--fromDatePicker-cal--Head-next')]")).click();
            driver.findElement(By.xpath("//div[contains(@id,'-fromDatePicker-cal--Month0-') and contains (@id,'09')]")).click();
        }else {
            prevmonth.click();
            picyear.click();
            changeyear.click();//2017
            driver.findElement(By.xpath("//div[contains(@id,'-fromDatePicker-cal--Month0-') and contains (@id,'29')]")).click();
        }

        fromdateok.click();
    }

    public static void pic_To_Date() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(tosessiondatetime));
        tosessiondatetime.click();
        wait.until(ExpectedConditions.elementToBeClickable(tosessiondatetime));
       driver.findElement(By.xpath("//button//span[contains(@id,'__jsview1--toDatePicker-OK-inner')]")).click();

    }
    public static void filter_Facility(String facility) {
        driver.navigate().refresh();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//div[contains(@class,'sapFacilitySelectionButton sapMBarChild sapMMenuBtn sapMMenuBtnRegular')]")).click();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> list = driver.findElements(By.xpath("//div[contains(@class,'sapUiMnu')]"));
        for (WebElement f : list) {
            if (f.getText().equals(facility)) {
                f.click();
                result = 0;
                break;
            }
        }
   }
   public static void export_Csv(String verify) {
       try {
           Thread.sleep(200);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       driver.findElement(By.xpath("//button[contains(@title,'Export to CSV File')]")).click();


       if (verify == "yes") {
           popuptext = driver.findElement(By.xpath("//span[contains(@class,'sapMMsgBoxText sapMText sapMTextMaxWidth sapUiSelectable')]")).getText();

           if (popuptext.equals("Select a date and time range to export parking sessions")) {
               result = 2;
               driver.findElement(By.xpath("//button[contains(@id,'__mbox-btn')]")).click();
           } else if (popuptext.equals("Date range must be less than one year"))
           {
               result=3;
               driver.findElement(By.xpath("//button[contains(@id,'__mbox-btn')]")).click();
       }
   }else if(verify=="no"){

           String folderName = "C://Users//i351187//Downloads";
           File[] listFiles = new File(folderName).listFiles();

           for (int i = 0; i < listFiles.length; i++) {
               if (listFiles[i].isFile()) {
                   String fileName = listFiles[i].getName();
                   if (fileName.startsWith("TransactionsView")) {
                       result =0;
                   break;}
                       else {result=1;}
                   }
               }

       }

       }
    public static void delete_File(String filenamestart) {
        String folderName = "C://Users//i351187//Downloads";
        File[] listFiles = new File(folderName).listFiles();

        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isFile()) {
                String fileName = listFiles[i].getName();
                if (fileName.startsWith(filenamestart)) {
                    listFiles[i].delete();
                }
            }
        }
    }

    public static int checkExists(String directory, String file) {
        File dir = new File(directory);
        File[] dir_contents = dir.listFiles();
        String temp = file;
        boolean check = new File(directory, temp).exists();
        System.out.println("Check"+check);  // -->always says false

        for(int i = 0; i<dir_contents.length;i++) {
            if(dir_contents[i].getName() == (file))
                return 0;
        }
        return 1;
    }
}




