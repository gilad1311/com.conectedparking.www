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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class NewFacilityFunctions {
    private static WebDriver driver;
    private static LoginPage loginPage;

    @Rule
    public TestName name = new TestName();
    // create ExtentReports and attach reporter(s)
    private static ExtentReports extent;
    private static FacilitiesPage facilitiesPage;
    private static ParkingLotEditPage parkingLotEditPage;
    private static NewFacilityManagerPage newFacilityManagerPage;

    // creates a toggle for the given test, adds all log events under it
    private static ExtentTest test;
    private ExtentTest test1;

    @BeforeClass

    public static void beforeClass() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Constants.FACILITYEDITREPORTLOCATION);
        // choose to append each test
        htmlReporter.setAppendExisting(true);
        // attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // name your test and add description
        test = extent.createTest("Facility & LOT Editing", "new functionality for facility and LOT ");

        // add custom system info
        extent.setSystemInfo("Environment", "Dev");
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
            facilitiesPage=PageFactory.initElements(driver,FacilitiesPage.class);
            loginPage = PageFactory.initElements(driver, LoginPage.class);
            parkingLotEditPage=PageFactory.initElements(driver,ParkingLotEditPage.class);
            newFacilityManagerPage=PageFactory.initElements(driver,NewFacilityManagerPage.class);
        }
    }
    @Test
    public void test01_log_In() {
        loginPage.log_In(Constants.MAILBOB);
        if (LoginPage.result == 0) {
            try {
                test.pass("Login is OK", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH  + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (LoginPage.result == 1) {
            System.out.println("problem with log in");
            try {
                test.fail("Login IS NOT OK", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH  + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void test02_Edit_Facility(){
    facilitiesPage.click_To_Facilities_Page();
    facilitiesPage.go_To_Edit_Facility("SAP IL");
        if (facilitiesPage.result == 0) {
            try {
                test.pass("found SAP IL", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (facilitiesPage.result == 1) {
            System.out.println("problem with log in");
            try {
                test.fail("did not find or cannot edit SAP IL", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH  + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    facilitiesPage.edit_Facility();
    facilitiesPage.edit_General_Info("SAP IL update");
        if (facilitiesPage.result == 0) {
            try {
                test.pass("edit general info for SAP IL", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (facilitiesPage.result == 1) {
            System.out.println("problem with log in");
            try {
                test.fail(" cannot edit general info for SAP IL", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH  + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @Test
    public void test03_Goto_Edit_Parking_Lot(){
        facilitiesPage.click_To_Facilities_Page();
        facilitiesPage.go_Edit_Updated_Facility("SAP IL update");
        if (facilitiesPage.result == 0) {
            try {
                test.pass("found SAP IL update", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (facilitiesPage.result == 1) {
            System.out.println("problem with log in");
            try {
                test.fail(" cannot find SAP IL update", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH  + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        parkingLotEditPage.click_To_Parking_Lot_Edit();
    }
    @Test
    public void test04_Alter_Capacity() {
        parkingLotEditPage.edit_Capacity();
        if (parkingLotEditPage.result == 0) {
            try {
                test.pass("lot's capacity altered as expected", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH  + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (parkingLotEditPage.result == 1) {
            try {
                test.fail("lot's capacity CANNOT be altered as expected", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH  + name.getMethodName())).build());
                test.log(Status.INFO, parkingLotEditPage.popuptext);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @Test
    public void test05_Alter_Policy(){
        parkingLotEditPage.edit_Policy();
        if (parkingLotEditPage.result == 0) {
            try {
                test.pass("lot's Policy altered as expected", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (parkingLotEditPage.result == 1) {
            try {
                test.fail("lot's Policy CANNOT be altered as expected", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());
                test.log(Status.INFO, parkingLotEditPage.popuptext);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test06_Reset_Info(){
        facilitiesPage.click_To_Facilities_Page();
        facilitiesPage.click_To_Facilities_Page();
        facilitiesPage.go_Edit_Updated_Facility("SAP IL update");
        facilitiesPage.edit_Facility();
        facilitiesPage.reset_General_Info("SAP IL");
        if (facilitiesPage.result == 0) {
            try {
                test.pass("General Info Reseted ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH  + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (facilitiesPage.result == 1) {
            try {
                test.fail("General Info DIDNOT GOT Reseted as expected", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        parkingLotEditPage.click_To_Parking_Lot_Edit();
        parkingLotEditPage.reset_Capacity("45","5","8","22");
        parkingLotEditPage.edit_Policy();

        if (parkingLotEditPage.result == 0) {
            try {
                test.pass("Capacity Info Reseted ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (parkingLotEditPage.result == 1) {
            try {
                test.fail("Capacity Info DIDNOT GOT Reseted as expected", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(Constants.IMAGESPATH + name.getMethodName())).build());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        @AfterClass
        public static void afterClass() {
                       driver.quit();
//             build and flush report
            extent.flush();
            try {
                 Mail.send_Report("Facility & Lot Edit Sanity");
            } catch (Exception e) {
                e.printStackTrace();
            }
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
