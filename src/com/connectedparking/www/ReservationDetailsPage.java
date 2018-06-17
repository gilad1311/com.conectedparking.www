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

public class ReservationDetailsPage {
    private static WebElement element=null;
    public static int result =1;
    private static  WebDriver driver;
    public static String popuptext;


    // using @findBy annotaion to find element
    @FindBy(how = How.ID, using = Constants.OPEN_GATE_ID)
    private  static WebElement opengate;
    @FindBy(how = How.XPATH, using = Constants.OPEN_GATE_DESCRIPTION)
    private  static WebElement descritopn;
    @FindBy(how = How.ID, using = Constants.POPUP_OPEN_GATE_ID)
    private  static WebElement popupopengate;



    public ReservationDetailsPage(WebDriver driver){// constructor
        this.driver=driver;
    }
    public static void open_Gate() {
        try {
            WebDriverWait wait= new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(Constants.OPEN_GATE_ID)));
            opengate.click();
            Thread.sleep(300);
            descritopn.click();
            descritopn.sendKeys("This is a test to open the gate");
            Thread.sleep(300);
            popupopengate.click();
            result = 0;
            Thread.sleep(300);
        } catch (NoSuchElementException e) {
            result = 1;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            if (IsVisible(By.id("__mbox0-header"))){
                WebElement popupclick = driver.findElement(By.xpath("//span[contains (@class,'sapMMsgBoxText') and contains (@class,'sapMText')]"));
                popuptext = popupclick.getText();
                driver.findElement(By.id("__mbox-btn-1-inner")).click();
                WebElement alredyinpop = driver.findElement(By.id("__text26"));
                popuptext = alredyinpop.getText();
                driver.findElement(By.id("__mbox-btn-1-inner")).click();
                driver.findElement(By.id("__button31")).click();
                result = 1;
            }else {
                result=0;
            }

        }catch (NoSuchElementException a){
                a.printStackTrace();
                result=0;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        }



    /**
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

