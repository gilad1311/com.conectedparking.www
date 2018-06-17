package com.connectedparking.www;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import static javax.print.attribute.standard.MediaSizeName.C;
import static org.junit.Assert.fail;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class ReressionTest {


    private static WebDriver driver;

    @Rule
    public TestName name = new TestName();
    // create ExtentReports and attach reporter(s)
    private static ExtentReports extent;
    private static GeneralCode generalCode;
    private static FacilitiesPage facilitiesPage;
    private static Api api;
    private static LoginPage loginPage;
    private static AlertsPage alertsPage;
    private static ReservationsPage reservationsPage;
    private static InnerReservationPage innerReservationPage;
    private static ReservationDetailsPage reservationDetailsPage;
    private static RemoteGateAccessPage remoteGateAccessPage;
    private static EventsPage eventsPage;
    private static ParkingSessionsPage parkingSessionsPage;
    private static ParkingLotEditPage parkingLotEditPage;
    private static MonthlyParkersPage monthlyParkersPage;
    private static RestClientService restClientService;
    private static OpenGatePage openGatePage;
    private static Mail mail;
    // creates a toggle for the given test, adds all log events under it
    private static ExtentTest test;
    private ExtentTest test1;

    @BeforeClass

    public static void beforeClass() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Constants.REGRESSIONTESTREPORT);
        // choose to append each test
        htmlReporter.setAppendExisting(true);
        // attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // name your test and add description
        test = extent.createTest("Regression test", "Ver. 1.7");

        // add custom system info
        extent.setSystemInfo("Environment", "Verification");
        extent.setSystemInfo("Tester", "Gilad");

        // log results
//            test.log(Status.INFO, "@Before class");

        boolean driverEstablish = false;

        try {
            System.setProperty(Constants.CHROMEDRIVER,Constants.CHROMEDRIVERPATH);

           String pathToExtension="C://Users//i351187//AppData//Local//Google//Chrome//User Data//Default//Extensions//aejoelaoggembcahagimdiliamlcdmfm//2.17.2_0.crx";
            ChromeOptions options = new ChromeOptions();
            options.addExtensions(new File(pathToExtension));

            options.addArguments("--lang=en");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();

            driver.get("https://validationade8ebb32-aee97c2c5.hana.ondemand.com/admin/#/alerts");
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
            reservationsPage = PageFactory.initElements(driver, ReservationsPage.class);
            innerReservationPage = PageFactory.initElements(driver, InnerReservationPage.class);
            reservationDetailsPage = PageFactory.initElements(driver, ReservationDetailsPage.class);
            eventsPage = PageFactory.initElements(driver, EventsPage.class);
            parkingSessionsPage = PageFactory.initElements(driver, ParkingSessionsPage.class);
            parkingLotEditPage=PageFactory.initElements(driver,ParkingLotEditPage.class);
            monthlyParkersPage=PageFactory.initElements(driver,MonthlyParkersPage.class);
            facilitiesPage = PageFactory.initElements(driver, FacilitiesPage.class);
            api=PageFactory.initElements(driver,Api.class);
            loginPage=PageFactory.initElements(driver,LoginPage.class);
            restClientService=PageFactory.initElements(driver,RestClientService.class);
            openGatePage=PageFactory.initElements(driver,OpenGatePage.class);
            mail=PageFactory.initElements(driver,Mail.class);
            generalCode=PageFactory.initElements(driver,GeneralCode.class);
            remoteGateAccessPage=PageFactory.initElements(driver,RemoteGateAccessPage.class);
            alertsPage=PageFactory.initElements(driver,AlertsPage.class);

        }


    }
    @Test
    public void test01_log_In() {//test01_log_In_3454
        loginPage.log_In(Constants.MAILGENERAL);
        if (LoginPage.result == 0) {
            try {
                test.pass("Login is OK", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH+ name.getMethodName())).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (LoginPage.result == 1) {
            System.out.println("problem with log in");
            try {
                test.fail("Login IS NOT OK", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//    @Test
//    public void test02_Open_Gate_Blocked_Parker_P2913() {
//        monthlyParkersPage.click_To_Monthly_Parkers();
//        monthlyParkersPage.add_New_Parker_Info("Blocked");
//        monthlyParkersPage.open_Gate();
//        openGatePage.monthly_parker_open_gate("SAP IL","SAP IL H3","Entrance");
//        monthlyParkersPage.open_Gate();
//        openGatePage.monthly_parker_open_gate("SAP IL","SAP IL H3","Exit");
//        eventsPage.click_To_Events();
//        eventsPage.general_Search_Event(monthlyParkersPage.createdmonthlyparke,"Exit");
//        parkingSessionsPage.click_To_Parking_Sessions();
//        parkingSessionsPage.search_And_Verify_Session("Manually Closed","Manually Closed",monthlyParkersPage.createdmonthlyparke);
//            if (parkingSessionsPage.result == 0) {
//            try {
//                test.pass("Event/Parking Session found for Blocked Monthly Parker Exit", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH+ name.getMethodName())).build());
//                test.log(Status.INFO, "PARCPROJECT-2913" + "Open Gate to Blocked monthly parker");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else if (parkingSessionsPage.result == 1) {
//
//            try {
//                test.fail("Event/Parking Session NOT found for Blocked Monthly Parker Exit", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Test
//    public void test03_Cancel_reservation() {//test03_Cancel_reservation_P1901
//        reservationsPage.go_To_Reservation();
//        reservationsPage.add_New_Reservation();
//        innerReservationPage.is_Add_Reservation_Page();
//        innerReservationPage.pick_Lot_A_Facility( "SAP IL","SAP IL H3");
//        innerReservationPage.pick_From_Date();
//        innerReservationPage.pick_To_Date();
//        innerReservationPage.set_Personal_Info();
//        innerReservationPage.save_Reservation();
//        if (innerReservationPage.result == 0) {
//            try {
//                test.pass("New reservation created and saved as expected-_P1901", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else if (innerReservationPage.result == 1) {
//            try {
//                test.fail("New reservation DID NOT GET created-_P1901", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//                test.log(Status.INFO, InnerReservationPage.popuptext);
//                System.out.println(InnerReservationPage.popuptext);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        reservationsPage.set_Status("Reserved");
//        reservationsPage.search();
//        if (reservationsPage.result == 0) {
//            try {
//                test.pass("Automation reservation found in the reservation screen", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else if (reservationsPage.result == 1) {
//            try {
//                test.fail("CANNOT find the new reservation found in the reservation screen", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        reservationsPage.cancel_Reservation();
//        if (reservationsPage.result == 0) {
//            try {
//                test.pass("Reservation canceled as expected ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else if (reservationsPage.result == 1) {
//            try {
//                test.fail("CANNOT  canceled Reservation ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    @Test//this test verify the ability to export CSV -not the file values!
//    public void test04_Export_Csv_Transactions_W_Various_Filters_P2416() {
//        restClientService.imp();
//        if (restClientService.result == 0) {
//                test.log(Status.INFO, "Jason file imported");
//        } else if (restClientService.result == 1) {
//                test.log(Status.INFO, "CANNOT  import the Jason file  ");
//        }
//            restClientService.sendSubRest("create 2415");
//            if (restClientService.result == 0) {
//                    test.log(Status.INFO, "Rest file sent");
//            } else if (restClientService.result == 1) {
//                    test.log(Status.INFO, "CANNOT  send the Rest file ");
//            }
//                parkingSessionsPage.click_To_Parking_Sessions();
//                parkingSessionsPage.filter_Facility("JIRA-2415- facility");
//                parkingSessionsPage.export_Csv("yes");
//                if (parkingSessionsPage.result == 0) {
//                    try {
//                        test.pass("CSV file got exported", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//                    } catch (IOException j) {
//                        j.printStackTrace();
//                    }
//                } else if (parkingSessionsPage.result == 1) {
//                    try {
//                        test.fail("CANNOT  export the CSV file  ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//                        test.log(Status.INFO, ParkingSessionsPage.popuptext);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else if (parkingSessionsPage.result == 2) {
//                    try {
//                        test.pass("CSV file cannot be exported .", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//                        test.log(Status.INFO, ParkingSessionsPage.popuptext);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//        parkingSessionsPage.pick_From_Date("");
//        parkingSessionsPage.pic_To_Date();
//        parkingSessionsPage.export_Csv("yes");
//        if (parkingSessionsPage.result == 0) {
//            try {
//                test.pass("CSV file got exported", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//            } catch (IOException j) {
//                j.printStackTrace();
//            }
//        } else if (parkingSessionsPage.result == 1) {
//            try {
//                test.fail("CANNOT  export the CSV file  ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//                test.log(Status.INFO, ParkingSessionsPage.popuptext);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else if (parkingSessionsPage.result == 3) {
//            try {
//                test.pass("CSV file cannot be exported .", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//                test.log(Status.INFO, ParkingSessionsPage.popuptext);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        parkingSessionsPage.pick_From_Date("y");
//        parkingSessionsPage.pic_To_Date();
//        parkingSessionsPage.export_Csv("no");
//        if (parkingSessionsPage.result == 0) {
//
//            test.log(Status.INFO, "CSV file got exported");
//        } else if (parkingSessionsPage.result == 1) {
//            test.log(Status.INFO, "CANNOT  export the CSV file  ");
//            test.log(Status.INFO, ParkingSessionsPage.popuptext);
//        }
//                generalCode.switchTab(1);
//                restClientService.sendSubRest("delete 2415");
//                parkingSessionsPage.delete_File("TransactionsView");
//            }

//    @Test
//    public void test05_Add_Resrvation_When_Full_P3393() {
//       facilitiesPage.click_To_Facilities_Page();
//        facilitiesPage.go_To_Edit_Facility("SAP IL");
//        parkingLotEditPage.click_To_Parking_Lot_Edit();
//        parkingLotEditPage.reset_Capacity("45","5","0","22");
//        parkingLotEditPage.edit_Policy();
//        reservationsPage.go_To_Reservation();
//        reservationsPage.add_New_Reservation();
//        innerReservationPage.pick_Lot_A_Facility("SAP IL","SAP IL H3");
//        innerReservationPage.pick_From_Date();
//        innerReservationPage.pick_To_Date();
//        innerReservationPage.set_Personal_Info();
//        innerReservationPage.expected_Error_After_Save();
//        if (innerReservationPage.result == 0) {
//            try {
//                test.pass(" As expected New reservation cannot be created", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//                test.log(Status.INFO, InnerReservationPage.popuptext+" Add_Resrvation_When_Full_P3393");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else if (innerReservationPage.result == 1) {
//            try {
//                test.fail("New reservation got created-NOTOK", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        facilitiesPage.click_To_Facilities_Page();
//        facilitiesPage.go_To_Edit_Facility("SAP IL");
//        parkingLotEditPage.click_To_Parking_Lot_Edit();
//        parkingLotEditPage.reset_Capacity("45","5","20","22");
//        parkingLotEditPage.reset_Policy();
//
//    }
        @Test
        public void test06_Remote_Gate_Access() {
        remoteGateAccessPage.open_Gate();
        alertsPage.go_to_Alert();
        alertsPage.verify_Alert("Gate Opened Manually");






        }




            //public void test05_Run_Rest() {
        //        restClientService.imp();
        //        restClientService.sendSubRest("Create 1654");
        //        restClientService.clearTestData();
        //        restClientService.setTestData();
        //        restClientService.getBodyMessage();
        //        restClientService.setScheme();

        static String takeScreenShot(String ImagesPath) {
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

    // Draws a red border around the found element. Does not set it back anyhow.
    public WebElement findElement(By by) {
        WebElement elem = driver.findElement(by);
        // draw a border around the found element
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", elem);
        }
        return elem;
    }

    @AfterClass
    public static void afterClass() {
        //           driver.quit();
//             build and flush report
        extent.flush();
        mail.send_Report("Regression Report");
    }
}