package com.connectedparking.www;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoteGateAccessPage {
    private static WebElement element = null;
    public static int result = 1;
    private static WebDriver driver = null;
    public static String popuptext = "";

    @FindBy(how = How.XPATH, using = Constants.REMOTEACCESSNAV)
    private static WebElement goToRemote;


    public RemoteGateAccessPage(WebDriver driver) {// constructor
        this.driver = driver;
    }

    public static void open_Gate() {
        try {
            WebDriverWait waits = new WebDriverWait(driver, 700);
            waits.until(ExpectedConditions.elementToBeClickable(goToRemote));
            goToRemote.click();
            GeneralCode.sleep(400);
            driver.findElement(By.xpath("//span[contains(@id,'--facilitiesSelect-arrow')]")).click();
            GeneralCode.selectFromList("//li[contains(@class,'sapMSelectListItem')]", "SAP IL");
            driver.findElement(By.xpath("//span[contains(@id,'-lotsSelect-arrow')]")).click();
            GeneralCode.selectFromList("//li[contains(@class,'sapMSelectListItem')]", "SAP IL H3");
            driver.findElement(By.xpath("//span[contains(@id,'-remoteAccessOpenGateButton-inner')]")).click();
            result = 0;
            GeneralCode.sleep(700);
        } catch (NoSuchElementException r) {
            r.printStackTrace();
            result = 1;
        }

    }

}
