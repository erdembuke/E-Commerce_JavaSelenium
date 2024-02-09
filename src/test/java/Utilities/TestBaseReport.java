package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class TestBaseReport {

    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;
    protected static ExtentHtmlReporter extentHtmlReporter;

    @BeforeTest(alwaysRun = true)
    protected void setupTest() {
        extentReports = new ExtentReports(); // extent reports baslatir

        // Define file path for HTML report
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String filePath = System.getProperty("user.dir") + "/test-output/Rapor" + date + ".html";
        extentHtmlReporter = new ExtentHtmlReporter(filePath); // start HTML reporter for file path
        extentReports.attachReporter(extentHtmlReporter);

        // Additional system infos for report
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Navigator", ConfigReader.getProperty("browser"));
        extentReports.setSystemInfo("Engineer", "Erdem");
        extentHtmlReporter.config().setDocumentTitle("E2E Testing"); // Set document title
        extentHtmlReporter.config().setReportName("TestNG Reports"); // Set report name

    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result) throws IOException {
        // If test failed, take screenshot and add to the report
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotLocation = ReusableMethods.getScreenshot(result.getName());
            extentTest.fail(result.getName()); // Save test as failed
            extentTest.addScreenCaptureFromPath(screenshotLocation); // add screenshot to the report
            extentTest.fail(result.getThrowable()); // save error details
        } else if (result.getStatus() == ITestResult.SKIP) { // save skipped test
            extentTest.skip("Test Status Skip: " + result.getName());
        }

        GWD.closeDriver(); // close driver after every test method
    }

    // For completing the report, save and quit
    @AfterTest(alwaysRun = true)
    protected void tearDownTest() {
        extentReports.flush();
    }


}
