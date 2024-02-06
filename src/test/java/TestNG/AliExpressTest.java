package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AliExpressTest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://tr.aliexpress.com");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void productSearchTest() {
        String searchKeyword = "telefon";
        WebElement declineSubscriptionBtn = driver.findElement(By.xpath("//div[text()=\"İzin verme\"]"));
        if (declineSubscriptionBtn.isDisplayed())
            declineSubscriptionBtn.click();

        /* sayfanin altinda acilan pencereyi bulma
        String mainWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindowHandle)){
                driver.switchTo().window(windowHandle);
                break;
            }
        }
         */
        WebElement searchBox = driver.findElement(By.id("search-words"));
        WebElement searchBtn = driver.findElement(By.cssSelector("input[type=\"button\"]"));

        searchBox.sendKeys(searchKeyword);
        searchBtn.click();



    }
}
