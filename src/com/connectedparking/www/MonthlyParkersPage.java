package com.connectedparking.www;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

public class MonthlyParkersPage {
    private static WebElement element = null;
    public static int result = 1;
    private static WebDriver driver;
    public static String popuptext;
    private static long time;
    private static String timename;
    public static String createdmonthlyparke;
    private static String parkernametofind;


    // using @findBy annotaion to find element
    @FindBy(how = How.ID, using = Constants.MONTHLYPARKERNAV)
    private static WebElement monthlyparkersnav;
    @FindBy(how = How.XPATH, using = Constants.ADDMONTHLYPBUTTON)
    private static WebElement addnewbutton;
    @FindBy(how = How.XPATH, using = Constants.SELECTFACILITYTOJOIN)
    private static WebElement facilitytojoin;
    @FindBy(how = How.ID, using = Constants.MONTHLYADDVEHICLE)
    private static WebElement addviehicle;
    @FindBy(how = How.ID, using = Constants.MONTHLYEMAIL)
    private static WebElement monthlymail;
    @FindBy(how = How.ID, using = Constants.MONTHLYLICENSEPLATE)
    private static WebElement monthlylicensplate;
    @FindBy(how = How.ID, using = Constants.MONTHLYPFIRSTNAME)
    private static WebElement monthlyfirstname;
    @FindBy(how = How.ID, using = Constants.MONTHLYPHONE)
    private static WebElement monthlyphone;
    @FindBy(how = How.ID, using = Constants.MONTHLYPLASTNAME)
    private static WebElement monthlylastname;
    @FindBy(how = How.ID, using = Constants.MONTLYSAVE)
    private static WebElement monthlylsavenew;
    @FindBy(how = How.ID, using = Constants.MONTLYNFC)
    private static WebElement monthlynfc;
    @FindBy(how = How.XPATH, using = Constants.MONTLYQRCODE)
    private static WebElement monthlyqr;
    @FindBy(how = How.XPATH, using = Constants.SEARCHMONTHLYP)
    private static WebElement searchmonthlyp;
    @FindBy(how = How.ID, using = Constants.EDITMONTHLYPARKERNAVE)
    private static WebElement editmonthlyp;
    @FindBy(how = How.ID, using = Constants.MONTHLYCOUNTRY)
    private static WebElement opencountrylist;



    public MonthlyParkersPage(WebDriver driver) {// constructor
        this.driver = driver;
    }

    public static void click_To_Monthly_Parkers() {
        monthlyparkersnav.click();
    }

    public static void add_New_Parker_Info(String status) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 300);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.ADDMONTHLYPBUTTON)));
            Thread.sleep(60);
            addnewbutton.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.SELECTFACILITYTOJOIN)));
            facilitytojoin.click();
            wait.until(ExpectedConditions.elementToBeClickable((By.id("RegisterDriverCreate--subscriptionStatusSelect-arrow"))));
            driver.findElement(By.id("RegisterDriverCreate--subscriptionStatusSelect-arrow")).click();
            Thread.sleep(90);
            List<WebElement> typelist = driver.findElements(By.xpath("//ul//li[contains (@aria-setsize,'4')]"));
            for (WebElement opt : typelist) {
                if (opt.getText().equals(status)) {
                    opt.click();
                    result = 0;
                    break;
                }
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", opencountrylist);
            Thread.sleep(100);
            opencountrylist.click();
            List<WebElement> countrylist = driver.findElements(By.xpath("//ul//li[contains (@aria-posinset,'107')]"));
            for (WebElement cont : countrylist) {
                if (cont.getText().equals("Israel")) {
                    cont.click();
                    result = 0;
                    break;
                }
            }
            time = (System.currentTimeMillis() / 1000);
            timename = String.valueOf(time);
            monthlylastname.sendKeys("Auto");
            monthlyphone.sendKeys(Constants.PHONENUMBER);
            createdmonthlyparke = timename + "a";

            monthlyfirstname.sendKeys(createdmonthlyparke);
            monthlymail.sendKeys(Constants.SENDTOMAIL);
            addviehicle.click();
            monthlylicensplate.sendKeys(timename);
            WebElement element = driver.findElement(By.id("RegisterDriverCreate--licensePlateCountry-RegisterDriverCreate--editVehiclesList-0-label"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
            Thread.sleep(300);
            List<WebElement> vcountrylist = driver.findElements(By.xpath("//ul//li[contains (@aria-posinset,'107')]"));
            for (WebElement cont : vcountrylist) {
                if (cont.getText().equals("Israel")) {
                    cont.click();
                    result = 0;
                    break;
                }
            }
            monthlylicensplate.sendKeys(Keys.PAGE_DOWN);
            Thread.sleep(300);
            wait.until(ExpectedConditions.elementToBeClickable(By.id(Constants.MONTLYNFC)));
            monthlynfc.click();

            monthlylsavenew.click();
            result = 0;

        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
            result = 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            result = 1;
        } catch (TimeoutException t) {
            t.printStackTrace();
            result = 1;
        }

    }

    public static void search_Monthly_Parker() {
        try {
            Thread.sleep(500);
            searchmonthlyp.click();
            Thread.sleep(500);
            searchmonthlyp.sendKeys(createdmonthlyparke);
            Thread.sleep(500);
            parkernametofind = driver.findElement(By.xpath("//span[@class='sapUxAPObjectPageHeaderContentItem']/h1/span[contains(@id,'monthlyDriverHeader-innerTitle')]")).getText();
            String parkernametofindtoverify = createdmonthlyparke + " Auto";
            if (parkernametofind.equals(parkernametofindtoverify))
                result = 0;
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void edit_Monthly_Parker() {

        try {
            click_To_Monthly_Parkers();
            Thread.sleep(1000);
            searchmonthlyp.sendKeys("Auto");
            Thread.sleep(90);

            editmonthlyp.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.id("RegisterDriverDetailsEdit--subscriptionStatusSelect-arrow")).click();
//            searchmonthlyp.click();
            List<WebElement> typelist = driver.findElements(By.xpath("//ul//li[contains (@aria-setsize,'4')]"));
            for (WebElement opt : typelist) {
                if (opt.getText().equals("Blocked")) {
                    opt.click();
                    result = 0;
                    break;
                }
            }
            driver.findElement(By.id("RegisterDriverDetailsEdit--editParkerCountry-arrow")).click();
            List<WebElement> countrylist = driver.findElements(By.xpath("//ul//li[contains (@aria-posinset,'82')]"));
            for (WebElement cont : countrylist) {
                if (cont.getText().equals("Germany")) {
                    cont.click();
                    result = 0;
                    break;
                }
            }
            WebElement element = driver.findElement(By.id("RegisterDriverDetailsEdit--licensePlateCountry-RegisterDriverDetailsEdit--editVehiclesList-0-arrow"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
            List<WebElement> vcountrylist = driver.findElements(By.xpath("//ul//li[contains (@aria-posinset,'82')]"));
            for (WebElement cont : vcountrylist) {
                if (cont.getText().equals("Germany")) {
                    cont.click();
                    result = 0;
                    break;
                }
            }
            driver.findElement(By.id("RegisterDriverDetailsEdit--monthlyDriverSaveAction-button-content")).click();

        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void delet_Monthly_Parker() {
        try {
            Thread.sleep(1000);
            click_To_Monthly_Parkers();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[contains(@id,'searchControl-reset')]")).click();
            searchmonthlyp.sendKeys();
            searchmonthlyp.sendKeys("Auto");
            Thread.sleep(90);
            WebElement delete = driver.findElement(By.xpath("//span[ contains(@id,'__action0-button-inner')]"));
            delete.click();
            driver.findElement(By.id("__mbox-btn-0")).click();
//            driver.findElement(By.xpath("//button[contains (@class,'sapMBarChild') and contains (@class,'sapMBtn') and contains (@class,'sapMBtnBase') and contains (@id,'__mbox-btn')]")).click();

            result = 0;
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    public static void open_Gate() {
        WebDriverWait wait = new WebDriverWait(driver, 300);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(Constants.EDITBUTTON)));
        driver.findElement(By.id(Constants.EDITBUTTON)).click();
        String error = driver.findElement(By.xpath("//span[contains(@style,'text-align:left')]")).getText();
        if (error.equals("Do you want to open this gate? This parker is currently blocked.")) {
            result = 0;
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[contains(text(),'OK')]")));
          driver.findElement(By.xpath("//bdi[contains(text(),'OK')]")).click();
        } else if (error != "Do you want to open this gate? This parker is currently blocked.") {
            result = 1;
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[contains(text(),'OK')]")));
            driver.findElement(By.xpath("//bdi[contains(text(),'OK')]")).click();
        }

    }
}



