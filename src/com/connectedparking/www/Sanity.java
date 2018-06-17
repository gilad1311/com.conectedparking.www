package com.connectedparking.www;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.connectedparking.www.AlertsPage;
import com.connectedparking.www.InnerReservationPage;
import com.connectedparking.www.LoginPage;
import com.connectedparking.www.ReservationsPage;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message.RecipientType;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Sanity {
    private static WebDriver driver;

    // create ExtentReports and attach reporter(s)
    private static ExtentReports extent;
    private static AlertsPage alertsPage;
    private static GeneralCode generalCode;
    private static LoginPage loginPage;
    private static ReservationsPage reservationsPage;
    private static InnerReservationPage innerReservationPage;
    private static ReservationDetailsPage reservationDetailsPage;
    private static EventsPage eventsPage;
    private static ParkingSessionsPage parkingSessionsPage;
    private static MonthlyParkersPage monthlyParkersPage;
    private static String enviroment;
    private static Mail mail;

    // creates a toggle for the given test, adds all log events under it
    private static ExtentTest test;
    private ExtentTest test1;

    @Rule
    public TestName name = new TestName();

    @BeforeClass

    public static void beforeClass() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Constants.SANITYREPORTLOCATION);
        // choose to append each test
        htmlReporter.setAppendExisting(true);
        // attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // name your test and add description
        enviroment="Validation";
        test = extent.createTest("Sanity", "Ver. 1.7");

        // add custom system info
        extent.setSystemInfo("Environment", enviroment);
        extent.setSystemInfo("Tester", "Gilad");

        // log results
//            test.log(Status.INFO, "@Before class");

        boolean driverEstablish = false;

        try {
            System.setProperty(Constants.CHROMEDRIVER,Constants.CHROMEDRIVERPATH);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=en");
            driver = new ChromeDriver(options);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();

            driver.get("https://"+enviroment+"ade8ebb32-aee97c2c5.hana.ondemand.com/admin/#/alerts");
            driverEstablish = true;
        } catch (Exception e) {
            e.printStackTrace();
            fail("Cant connect chromeDriver");

            driverEstablish = false;
        } finally {
            if (driverEstablish) {
            }
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
            alertsPage = PageFactory.initElements(driver, AlertsPage.class);
            loginPage = PageFactory.initElements(driver, LoginPage.class);
            reservationsPage = PageFactory.initElements(driver, ReservationsPage.class);
            innerReservationPage = PageFactory.initElements(driver, InnerReservationPage.class);
            reservationDetailsPage = PageFactory.initElements(driver, ReservationDetailsPage.class);
            eventsPage = PageFactory.initElements(driver, EventsPage.class);
            parkingSessionsPage = PageFactory.initElements(driver, ParkingSessionsPage.class);
            monthlyParkersPage=PageFactory.initElements(driver,MonthlyParkersPage.class);
            mail=PageFactory.initElements(driver,Mail.class);
            generalCode=PageFactory.initElements(driver,GeneralCode.class);
        }
    }

    @Test
    public void test01_log_In() {
        loginPage.log_In(Constants.MAILBOB);
        if (LoginPage.result == 0) {
            try {
                test.pass("Login is OK", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (LoginPage.result == 1) {
            try {
                test.fail("Login IS NOT OK", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH+ name.getMethodName())).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test02_Add_Reservation() {
        reservationsPage.add_New_Reservation();
        if (LoginPage.result == 0) {
            try {
                test.pass("clicked the add new reservation", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH+ name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (LoginPage.result == 1) {
            System.out.println("problem with new reservation button");
            try {
                test.fail(" The add new reservation button IS NOT OK", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test03_Create_New_Reservation() throws InterruptedException {
        innerReservationPage.is_Add_Reservation_Page();
        innerReservationPage.pick_Lot_A_Facility("SAP IL","SAP IL H3");
        innerReservationPage.pick_From_Date();
        innerReservationPage.pick_To_Date();
        innerReservationPage.set_Personal_Info();
        innerReservationPage.save_Reservation();
        if (innerReservationPage.result == 0) {
            try {
                test.pass("New reservation created and saved as expected", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (innerReservationPage.result == 1) {
            try {
                test.fail("New reservation DID NOT GET created", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
                test.log(Status.INFO, InnerReservationPage.popuptext);
                System.out.println(InnerReservationPage.popuptext);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                WebElement cancel = driver.findElement(By.id("__jsview3--monthlyDriverCancelAction-button"));
                WebDriverWait wait = new WebDriverWait(driver, 30);
                wait.until(ExpectedConditions.elementToBeClickable(cancel));
                cancel.click();
            }
        }
        reservationsPage.set_Status("Reserved");
        reservationsPage.search();
        if (reservationsPage.result == 0) {
            try {
                test.pass("Automation reservation found in the reservation screen", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH+ name.getMethodName())).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (reservationsPage.result == 1) {
            try {
                test.fail("CANNOT find the new reservation found in the reservation screen", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void test04_Open_Gate_To_New_Reservation_P1884() {
        reservationsPage.go_To_Reservation();
        reservationsPage.click_To_Reservation_Details();
        reservationDetailsPage.open_Gate();
        if (reservationDetailsPage.result == 0) {
            try {
                test.pass("The gate is open-1884", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH+ name.getMethodName())).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (reservationDetailsPage.result == 1) {
            try {
                test.fail("CANNOT open the gate", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
                test.log(Status.INFO, reservationDetailsPage.popuptext);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test05_Verify_Event_OPEN_GATE() {
        eventsPage.click_To_Events();
        eventsPage.search_Event();
        if (eventsPage.result == 0) {
            try {
                test.pass("Event got created ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH+ name.getMethodName())).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (eventsPage.result == 1) {
            try {
                test.fail("Event did not got created ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH+ name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

        @Test
       public  void test06_Verify_Parking_Session_Created() {
          parkingSessionsPage.click_To_Parking_Sessions();
           parkingSessionsPage.search_And_Verify_Session("Open","Open","");
           if (parkingSessionsPage.result == 0) {
               try {
                   test.pass("Fount te Parking Session with open status", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
               } catch (IOException e) {
                   e.printStackTrace();
               }
           } else if (parkingSessionsPage.result == 1) {
               try {
                   test.fail("No Open Parking Session found ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH+ name.getMethodName())).build());
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
    @Test
    public  void test07_Close_Parking_Session(){
        parkingSessionsPage.Close_Session();
        if (parkingSessionsPage.result == 0) {
            try {
                test.pass("Parking Session got closed", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (parkingSessionsPage.result == 1) {
            try {
                test.fail("No Open Parking Session to close or i could not close it ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @Test
    public void test08_Create_Monthly_Parker(){
        monthlyParkersPage.click_To_Monthly_Parkers();
        monthlyParkersPage.add_New_Parker_Info("Paying");
        if (monthlyParkersPage.result == 0) {
            try {
                test.pass("New Monthly parker got created", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (monthlyParkersPage.result == 1) {
            try {
                test.fail("Monthly parker CANNOT be created ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        monthlyParkersPage.search_Monthly_Parker();
        if (monthlyParkersPage.result == 0) {
            try {
                test.pass("The monthly parker have been found", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (monthlyParkersPage.result == 1) {
            try {
                test.fail("The monthly parker can not be found", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
       @Test
    public void test09_Edit_Monthly_Parker(){
        monthlyParkersPage.edit_Monthly_Parker();
        if (monthlyParkersPage.result == 0) {
            try {
                test.pass("Monthly member got edited and saved", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH+ name.getMethodName())).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (monthlyParkersPage.result == 1) {
            try {
                test.fail("Monthly member could not get edited or save", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void  test10_Delete_Monthly_parher(){
        monthlyParkersPage.delet_Monthly_Parker ();
        if (monthlyParkersPage.result == 0) {
            try {
                test.pass("Monthly parker got deleted", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH+ name.getMethodName())).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (monthlyParkersPage.result == 1) {
            try {
                test.fail("Monthly parker can not get deleted", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


        @AfterClass
        public static void afterClass() {
 //           driver.quit();
//             build and flush report
            extent.flush();
            mail.send_Report("Sanity Report");
        }

        private static String takeScreenShot(String ImagesPath) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(ImagesPath+".png");
            try {
                FileUtils.copyFile(screenShotFile, destinationFile);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return ImagesPath+".png";
        }

}



