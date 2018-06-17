package com.connectedparking.www;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GeneralCode {

    private static WebElement element = null;
    private static ArrayList<String> tabs;
    private static WebDriver driver;
    public static int result = 1;


    public GeneralCode(WebDriver driver) {// constructor
        this.driver = driver;
    }

    public static void selectFromList(String element,String value) {
        List<WebElement> direction = driver.findElements(By.xpath(element));
        for (WebElement v : direction) {
            if (v.getText().equals(value)) {
                v.click();
                result = 0;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                result = 1;
            }
        }
    }
    public static void switchTab(int number){
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(number));
    }
    public static void newTabswitch(int number){
        ((JavascriptExecutor) driver).executeScript("window.open()");
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(number));
    }
    public static void sleep(int time){
        try {
            Thread.sleep( time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

