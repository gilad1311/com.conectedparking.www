package com.connectedparking.www;

import com.connectedparking.www.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
    private static WebElement element=null;
    private static  WebDriver driver;
    public static int result =0;


    // using @findBy annotaion to find element
    @FindBy(how = How.ID, using = "j_username")
    private  static WebElement email;
    @FindBy(how = How.ID, using = Constants.PASSWORD_ID)
    private  static WebElement password;
    @FindBy(how = How.ID,using = Constants.LOGIN_BUTTON_ID)
    private static WebElement login;
    @FindBy(how=How.ID,using = Constants.ALERTPAGETITLE_ID)
    private static  WebElement alertpage;


    public LoginPage(WebDriver driver){// constructor
        this.driver=driver;
    }
    //insert methods per screen...
      static void log_In(String mail){
        email.click();
        email.sendKeys(mail);
        password.click();
        password.sendKeys(Constants.PASSWORD);
          try {
              Thread.sleep(200);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          login.click();
        if (alertpage.getText().equals("Alerts")){
            result=0;
        }else if (alertpage.getText()!="Alerts"){
            result=1;
            System.out.println("There was a problem in login, or the lending page is not Alerts");
        }


    }

}
