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

public class OpenGatePage {
    private static WebElement element = null;
    public static int result = 1;
    private static WebDriver driver;
    public static String popuptext;
    private static long time;
    private static String timename;
    private static String createdmonthlyparke;
    private static String parkernametofind;
    public static String info;

    // using @findBy annotaion to find element
    @FindBy(how = How.ID, using = Constants.OPENGATETITLE)
    private static WebElement title;

    public OpenGatePage(WebDriver driver) {// constructor
        this.driver = driver;
    }

    public static void monthly_parker_open_gate(String gateName, String parkigLot,String direction) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 300);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(Constants.OPENGATETITLE)));
            driver.findElement(By.id("openGatePage--facilitiesSelect-arrow")).click();
            try {
                Thread.sleep(90);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<WebElement> facilityl = driver.findElements(By.xpath("//li[contains (@id,'-openGatePage--facilitiesSelect')]"));
            for (WebElement opt : facilityl) {
                if (opt.getText().equals(gateName)) {
                    opt.click();
                    result = 0;
                    break;
                }
            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.xpath("//span[contains(@id,'openGatePage--lotsSelect-arrow')]")).click();
            List<WebElement> lotl = driver.findElements(By.xpath("//li[contains (@id,'-openGatePage--lotsSelect')]"));
            for (WebElement lot : lotl) {
                if (lot.getText().equals(parkigLot)) {
                    lot.click();
                    result = 0;
                    break;
                }
            }
            driver.findElement(By.id("openGatePage--openGateDescription-inner")).sendKeys("Automation test");
            driver.findElement(By.id("__action3-button")).click();
            result = 0;
            if (direction=="Exit"){
                 info="Exit gate got opened";
            }else {
                info="Entrance gate got opened";
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (NoSuchElementException f) {
            f.printStackTrace();
            result=1;
        }
    }
}
