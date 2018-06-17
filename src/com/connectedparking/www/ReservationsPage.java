package com.connectedparking.www;

import com.connectedparking.www.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.awt.windows.WEmbeddedFrame;

import java.util.List;
import java.util.NoSuchElementException;

public class ReservationsPage {
    private static WebElement element = null;
    public static WebElement licenseplate = null;
    private static WebDriver driver;
    public static int result = 1;


    // using @findBy annotaion to find element
    @FindBy(how = How.XPATH, using = Constants.RESERVATION_CREATE_RESERVATION_B)
    private static WebElement reservationspagetitle;
    @FindBy(how = How.XPATH, using = Constants.ADD_RESERVATION_BUTTON)
    private static WebElement addreservationbutton;
    @FindBy(how = How.XPATH, using = Constants.RESERVATIONNAVIGATION)
    private static WebElement clicktoreservation;
    @FindBy(how = How.XPATH, using = Constants.RESERVATION_SEARCH)
    private static WebElement reservationsearch;
    @FindBy(how = How.ID, using = Constants.RESERVATIONDATERANGE)
    private static WebElement reservationdate;



    public ReservationsPage(WebDriver driver) {// constructor
        this.driver = driver;
    }

    public static void go_To_Reservation() {
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clicktoreservation.click();
    }

    public static void add_New_Reservation() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(clicktoreservation));
        clicktoreservation.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(reservationspagetitle));
        addreservationbutton.click();
    }
    public static void set_Status(String status){
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.RESERVATION_SEARCH)));
        driver.findElement(By.xpath("//span[contains(@id,'--statusSelection-arrow')]")).click();
        GeneralCode.selectFromList("//li[contains(@id,'__item')]",status);
    }
    public static void search() {
        try {
            Thread.sleep(180);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        reservationdate.click();
//        reservationdate.sendKeys(Keys.ENTER);
        reservationsearch.click();
        reservationsearch.sendKeys(InnerReservationPage.searchname);
        try {
            Thread.sleep(180);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        licenseplate = driver.findElement(By.xpath("//td//span[contains(text(),'" + InnerReservationPage.searchname + "')]"));
        String found = licenseplate.getText();
        if (found.equals(InnerReservationPage.searchname)) {
            result = 0;
        } else if (!found.equals(InnerReservationPage.searchname)) {
            System.out.println("Reservation not found");
            result = 1;
        }

    }

    public static void click_To_Reservation_Details() {
        licenseplate.click();
    }

    public static void cancel_Reservation() {
        try {
            driver.findElement(By.xpath("//div[contains(@id,'-reservationsInfoTable--reservationTableRow-')]")).click();
            driver.findElement(By.xpath("//span[contains(@id,'reservationsInfoTable--cancelReservationButton-inner')]")).click();
            driver.findElement(By.xpath("//button[contains(@id,'__button')]//span[contains(@id,'inner')]//span[contains(@id,'content')]//bdi[contains(text(),'C')]")).click();
            result = 0;
        } catch (NoSuchElementException n) {
            n.printStackTrace();
            result = 1;
        }

    }
}
