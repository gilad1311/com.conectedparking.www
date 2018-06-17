package com.connectedparking.www;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NewFacilityManagerPage {
    private static WebElement element = null;
    private static WebDriver driver = null;
    public static int result = 1;

    @FindBy(how = How.ID, using = Constants.NEWMANAGERPERSONALINFOFM)
    private static WebElement personalinfofirstname;
    @FindBy(how = How.ID, using = Constants.NEWMANAGERPERSONALINFOLN)
    private static WebElement personalinfolastname;
    @FindBy(how = How.ID, using = Constants.NEWMANAGERPERSONALINFOPN)
    private static WebElement personalinfophon;
    @FindBy(how = How.ID, using = Constants.NEWMANAGERPERSONALINFOEMAIL)
    private static WebElement personalinfomail;
    @FindBy(how = How.ID, using = Constants.NEWMANAGERPERSONALINFOLANG)
    private static WebElement personalinfolanguage;
    @FindBy(how = How.ID, using = Constants.NEWMANAGERPERSONALINFOSAVE)
    private static WebElement personalinfosave;

    public NewFacilityManagerPage(WebDriver driver) {// constructor
        this.driver = driver;
    }


    public static void manager_Personal_Information() {
        personalinfofirstname.sendKeys("Test");
        personalinfolastname.sendKeys("Automation");
        personalinfomail.sendKeys("testautomation@mailinator.com");
        personalinfophon.sendKeys("09-121212");

        WebDriverWait wait = new WebDriverWait(driver, 300);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(Constants.NEWMANAGERPERSONALINFOLANG)));
        List<WebElement> lang = driver.findElements(By.id(Constants.NEWMANAGERPERSONALINFOLANG));
        for (WebElement w : lang) {
            //select language
            if (w.getText().equals("English (United State)")) {
                String langlist = w.getAttribute("id");
                wait.until(ExpectedConditions.elementToBeClickable(By.id(langlist)));
                driver.findElement(By.id(langlist)).click();
                result = 0;
                break; //English (United States)
            }
            personalinfosave.click();
        }
    }
}
