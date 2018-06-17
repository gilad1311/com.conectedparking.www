package com.connectedparking.www;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class AlertsPage {
    private static WebElement element = null;
    private static WebDriver driver = null;
    private int result = 1;


    // using @findBy annotaion to find element
    @FindBy(how = How.XPATH, using = Constants.ALERT_XPATH)
    private static WebElement alertsLeft;
    @FindBy(how = How.XPATH, using = Constants.ALERTTYPESELECT)
    private static WebElement alerttypselect;

    public AlertsPage(WebDriver driver) {// constructor
        this.driver = driver;
    }

    //insert methods per screen...
    public void go_to_Alert() {
        GeneralCode.sleep(2000);
        alertsLeft.click();
    }

    public void verify_Alert(String type) {
        LocalDate date = LocalDate.now();
        int v=date.getDayOfMonth();
        String today = String.valueOf(date.getDayOfMonth());
        if (v<10){today= "0"+today;}
        GeneralCode.sleep(2000);
        GeneralCode.selectFromList(Constants.ALERTTYPESELECT, type);
        GeneralCode.sleep(500);
        driver.findElement(By.xpath("//span[contains(@id,'-dateRangeSelect-icon')]")).click();
        driver.findElement(By.xpath("//div[contains(@id,'dateRangeSelect-cal--Month')and contains(@id,'"+today+"')]")).click();
        driver.findElement(By.xpath("//div[contains(@id,'dateRangeSelect-cal--Month')and contains(@id,"+today+")]")).click();




    }
}
