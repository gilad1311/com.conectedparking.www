package com.connectedparking.www;

import com.sun.deploy.util.SessionState;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class RestClientService {
    private static WebElement element = null;
    public static int result = 1;
    private static WebDriver driver;
    public static String popuptext;
    private static long time;
    private static String timename;
    private static String createdmonthlyparke;
    private static String parkernametofind;



    // using @findBy annotaion to find element
    @FindBy(how = How.XPATH, using = Constants.BODYTEST)
    private static WebElement bodytest;
    @FindBy(how = How.XPATH, using = Constants.SETCODE)
    private static WebElement setcode;
    @FindBy(how = How.XPATH, using = Constants.RESTSENDREQ)
    private static WebElement sendrequest;

    public RestClientService(WebDriver driver) {// constructor
        this.driver = driver;
    }

    public static void imp() {
        try {
            GeneralCode.newTabswitch(1);

            driver.get("chrome-extension://aejoelaoggembcahagimdiliamlcdmfm/restlet_client.html#requests");
            driver.findElement(By.className("icon-upload-alt")).click();
            List<WebElement> opptions = driver.findElements(By.xpath("//div//ul//li[contains(@e2e-tag,'btn-import-repository')]"));
            for (WebElement l : opptions) {
                if (l.getText().equals("Import Restlet Client Repository")) {
                    new Actions(driver).moveToElement(l).click().perform();

                    GeneralCode.selectFromList("//button[contains(@class,'r-btn')]", "Choose a file...");

                    StringSelection ss = new StringSelection("C:\\Users\\i351187\\Desktop\\json\\parcs tests data.json");
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

                    //native key strokes for CTRL, V and ENTER keys
                    Robot r = null;
                    r = new Robot();

                    r.keyPress(KeyEvent.VK_CONTROL);

                    r.keyPress(KeyEvent.VK_V);
                    r.keyRelease(KeyEvent.VK_V);
                    r.keyRelease(KeyEvent.VK_CONTROL);
                    r.keyPress(KeyEvent.VK_ENTER);
                    r.keyRelease(KeyEvent.VK_ENTER);

                    driver.findElement(By.xpath("//input[contains(@id,'gwt-uid-596')]")).click();
                    driver.findElement(By.xpath("//button[contains(@class,'r-btn-primary')]")).click();
                    driver.findElement(By.xpath("//i[contains(@class,'fa-caret-right')]")).click();
                    result = 0;
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
            result = 1;
        }
    }

    public static void sendSubRest(String toselect) {
        try {
            Thread.sleep(200);
            driver.findElement(By.xpath("//span[contains(@e2e-tag,'drive|" + toselect + "')]")).click();
            sendrequest.click();
            GeneralCode.switchTab(0);
            Thread.sleep(300);
            result = 0;
            driver.navigate().refresh();

        } catch (InterruptedException e) {
            e.printStackTrace();
            result = 1;
        }
    }

    public static void setScheme() {////update the path
        WebElement set = driver.findElement(By.xpath("//span[contains(class,'expression-input')]"));
        set.clear();
        set.sendKeys("https://validationade8ebb32.hana.ondemand.com/parc/v1/rest/RemoteTabletOperations/ClearTabletPreferences/33333c");
        driver.findElement(By.xpath("//a [contains(@e2e-tag,'btn-send')]")).click();
    }

    public static void getBodyMessage() {
        String response = driver.findElement(By.xpath("//div[contains(@class,'response-body-as-text')]")).getText();
        System.out.println(response);
    }

    public static void clearTestData() {
        Actions actions = new Actions(driver);
        actions.moveToElement(bodytest);
        actions.click();
        actions.sendKeys(Keys.CONTROL + "a");
        actions.sendKeys(Keys.BACK_SPACE);
        actions.build().perform();

    }

    public static void setTestData() {
        WebElement element = setcode;
        String script = "arguments[0].setAttribute('value', 'Set to this text.')";
        ((JavascriptExecutor) driver).executeScript(script, element);
//        actions.sendKeys("{" + "DataGenRequest" + ":" + "{testDataComponentID" + ":" + "2740" + "}}");

    }
}///add set scheme;send request;get info from responce;verify in App-add before scenario for what parker or reservation .....


