package com.bitwise.dominos.functionaltests.tests;

import com.bitwise.dominos.functionaltests.pageobject.CheckOutPage;
import com.bitwise.dominos.functionaltests.pageobject.DashboardPage;
import com.bitwise.dominos.functionaltests.pageobject.OrderSummaryPage;
import com.bitwise.dominos.functionaltests.pageobject.PizzasPage;
import com.bitwise.dominos.functionaltests.utilities.Utilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by samarthsi on 8/19/2016.
 */
public class FunctionalTests {

    private WebDriver driver;
    private ExtentTest logger;
    private ExtentReports report = new ExtentReports("Test_Reports\\FunctionalTestsReports.html");
    private Utilities util;
    private DashboardPage dashboardPage;
    private CheckOutPage checkOutPage;
    private PizzasPage pizzasPage;
    private OrderSummaryPage orderSummaryPage;

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        driver = new FirefoxDriver();
        driver.get("http://localhost:9035/UI");
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        util = new Utilities();
        dashboardPage = new DashboardPage(driver);
        checkOutPage = new CheckOutPage(driver);
        pizzasPage = new PizzasPage(driver);
        orderSummaryPage = new OrderSummaryPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {

            String screenshot_path = util.captureScrenshot(driver, result.getName());
            String image = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "Test Verification Failed" + result.getThrowable(), image);

        }else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
        } else {
            logger.log(LogStatus.PASS, "Test verification passed");
        }

        report.endTest(logger);
        report.flush();
        driver.quit();
    }

    @Test(enabled = true)
    public void orderFromDashboard(){
        logger = report.startTest("Functional Test 001","Verify if user is able to order pizza from Dashboard");
        dashboardPage.createZestyChickenPizzaOrder();
        logger.log(LogStatus.INFO, "Created an order with Zesty Chicken Pizza from Dashboard");
        pizzasPage.checkOutOrder();
        logger.log(LogStatus.INFO, "Checkout pizza order");
        checkOutPage.placePizzaOrder();
        logger.log(LogStatus.INFO, "Place pizza order");

        logger.log(LogStatus.INFO, "Verify the pizza ordered on order summary page");
        assert orderSummaryPage.getPizzaName("Zesty Chicken") : "Zesty chicken Pizza was not ordered successfully";
    }

    @Test(enabled = true)
    public void orderFromPizzasPage(){
        logger = report.startTest("Functional Test 002", "Verify if user is able to order pizza from Pizzas Page");
        pizzasPage.orderZestyChicken();
        logger.log(LogStatus.INFO, "Created an order with Zesty Chicken Pizza from pizzas page");
        pizzasPage.checkOutOrder();
        logger.log(LogStatus.INFO, "Checkout pizza order");
        checkOutPage.placePizzaOrder();
        logger.log(LogStatus.INFO, "Place pizza order");

        logger.log(LogStatus.INFO, "Verify the pizza ordered on order summary page");
        assert orderSummaryPage.getPizzaName("Zesty Chicken") : "Zesty chicken Pizza was not ordered successfully";
    }

}
