package com.connectedparking.www;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.concurrent.TimeUnit;

public class Api {
    private static WebElement element=null;
    private static WebDriver apidriver;
    public static int result =1;


    // using @findBy annotaion to find element
    @FindBy(how = How.CLASS_NAME, using = Constants.RESERVATION_CREATE_RESERVATION_B)
    private  static WebElement reservationspagetitle;






    public Api(WebDriver apidriver){// constructor
        this.apidriver=apidriver;
    }

    public static void open_Api(){
        apidriver=new ChromeDriver();
        apidriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String apiDriver=apidriver.getWindowHandle();
        apidriver.manage().window().maximize();
        apidriver.get("chrome-extension://aejoelaoggembcahagimdiliamlcdmfm/restlet_client.html");
    }
}
