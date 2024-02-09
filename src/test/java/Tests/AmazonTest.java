package Tests;

import Pages.AmazonPage;
import Utilities.ConfigReader;
import Utilities.GWD;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonTest {
    AmazonPage ap = new AmazonPage(); // for calling WebElements

    @Test(priority = 2) // prioritizing and test cases
    public void searchProduct() throws InterruptedException {
        // Navigate to Amazon
        GWD.getDriver().get(ConfigReader.getProperty("amazonUrl"));
        ap.rejectCookies.click();

        // Type "Apple MacBook M3" in Search Box
        ap.searchBox.sendKeys(ConfigReader.getProperty("textAmazon"));
        ap.searchBtn.click();
        Thread.sleep(500);

        // Assert the title contains searched product name
        String title = GWD.getDriver().getTitle();
        System.out.println("title -> " + title);
        Assert.assertTrue(title.contains(ConfigReader.getProperty("textAmazon")));

        String firstItemText = ap.firstItemAfterSearch.getText();
        System.out.println("Item: " + firstItemText);

        String itemPrice = "";
        itemPrice = ap.firstItemPricePt1.getText() + "," +
                ap.firstItemPricePt2.getText() + " TL";
        System.out.println("Price: " + itemPrice);

        /*
        Sayfanın ortasına scroll yapmak için:
        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
         */

        // Listing all search results
        int count = 0;
        for (WebElement titles : ap.productTitles) {
            String titleTxt = titles.getText();
            System.out.println("Item Title: " + titleTxt);
            count++;
            Assert.assertTrue(titleTxt.toLowerCase().contains("mac")); // every result contains mac
        }

        // Assertion of the search result total count
        System.out.println("item count -> " + count);
        Assert.assertEquals(count,ap.productTitles.size());

        GWD.quitDriver();
    }

    @Test(priority = 1) // prioritizing test cases
    public void loginNegativeTest() {
        // navigating to the login page
        GWD.getDriver().get(ConfigReader.getProperty("amazonUrl"));
        ap.loginNavBtn.click();

        // input e-mail and continue
        ap.inputMail.sendKeys(ConfigReader.getProperty("emailAmazon"));
        ap.continueBtn.click();

        // Assertion of alertbox
        Assert.assertTrue(ap.alertBox.isDisplayed());

    }


}
