package Day03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class N11Test {
    // JUnit jupiter used in this test class
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        // setup driver and navigate to the website before each test
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.n11.com");
    }

    @AfterEach
    public void tearDown() {
        // closing the driver after each test
        driver.quit();
    }

    @Test
    public void searchProductTest() {
        System.out.println("--- searchProductTest starts ---");
        // Assertion of the title
        String mainTitle = driver.getTitle();
        System.out.println(mainTitle);

        Assertions.assertTrue(mainTitle.toLowerCase().contains("n11"));

        // Searching product
        WebElement searchBox = driver.findElement(By.id("searchData"));
        searchBox.clear();
        searchBox.sendKeys(" Samsung S23 Ultra");

        WebElement searchBtn = driver.findElement(By.className("searchBtn"));
        searchBtn.click();

        // Products after search, Assertion of the results
        WebElement searchResult = driver.findElement(By.className("resultText"));
        WebElement searchResultNumber = driver.findElement(By.cssSelector(".resultText strong")); // number of the products found
        System.out.println("Actual: " + searchResult.getText());

        String expectedNumber = searchResultNumber.getText(); // 3,210 - 1,764 etc...
        String expectedResultFinal = "Samsung S23 Ultra için " + expectedNumber + " sonuç bulundu.";
        System.out.println("Expected: " + expectedResultFinal);
        Assertions.assertEquals(searchResult.getText(), expectedResultFinal);

        // Navigate back and return
        driver.navigate().back();
        driver.navigate().forward();

        System.out.println("--- searchProduct ends ---");
    }





}
