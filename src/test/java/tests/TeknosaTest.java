package tests;

import pages.TeknosaPage;
import utilities.ConfigReader;
import utilities.GWD;
import utilities.TestBaseReport;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TeknosaTest extends TestBaseReport {
    // EXTENT REPORT USED IN THIS TEST CLASS
    TeknosaPage tp = new TeknosaPage(); // for using Page Object Model and Reusable Methods

    @Test
    public void searchTest() {
        extentTest = extentReports.createTest("Teknosa Product Search Test");
        GWD.getDriver().get(ConfigReader.getProperty("teknosaUrl"));
        Assert.assertTrue(GWD.getDriver().getTitle().toLowerCase().contains("teknosa"));
        extentTest.info("User navigates to the main page of Teknosa");

        tp.switchToWindow(GWD.getDriver().getWindowHandle());
        tp.clickFunction(tp.closeBtnMainPage);
        tp.switchToWindow(GWD.getDriver().getWindowHandle());
        tp.clickFunction(tp.searchBox);
        tp.sendKeysFunction(tp.searchBox2, ConfigReader.getProperty("textTeknosa"));
        extentTest.info("Input text from properties file to the searchbox");

        tp.clickFunction(tp.searchBtn);
        extentTest.info("Search the product");



    }
}
