package com.connectedparking.www;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EventsPage {
    private static WebElement element = null;
    private static WebDriver driver = null;
    public static int result = 1;
    // using @findBy annotaion to find element
    @FindBy(how = How.XPATH, using = Constants.EVENTS_NAVIGATION)
    private static WebElement clickeventsnav;
    @FindBy(how = How.XPATH, using = Constants.SEARCH_EVENT)
    private static WebElement searchevent;
    @FindBy(how = How.ID, using = Constants.EVENTTYEPSELECTION)
    private static WebElement eventtype;

    public EventsPage(WebDriver driver) {// constructor
        this.driver = driver;
    }

    public static void click_To_Events() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickeventsnav.click();
    }

    public static void search_Event() {
        try {

            Thread.sleep(1000);
            searchevent.click();
            searchevent.sendKeys("Aut"+InnerReservationPage.timename);
            result = 0;
            Thread.sleep(1000);
        } catch (NoSuchElementException n) {
                n.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void general_Search_Event(String whatToSearch, String value) {
        try {

            Thread.sleep(1000);
            searchevent.click();
            searchevent.sendKeys(whatToSearch);
            List <WebElement> direction = driver.findElements(By.xpath("//span[contains (@class,'sapMText')and contains  (@class,'sapMTextBreakWord')]"));
            for (WebElement v : direction) {
                if (v.getText().equals(value)) {
                    result = 0;
                    break;
                }else {
                    result=1;
                }
            }
            Thread.sleep(1000);
        } catch (NoSuchElementException n) {
            n.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
