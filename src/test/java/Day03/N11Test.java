package Day03;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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


    // test note: after navigating cart, KVKK close button element is not interactable waiting to handle (Line 106)
    @Test
    public void addToCartAndDeleteTest() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Sendkeys "strap" to the searchbox and search for the products
        WebElement searchBox = driver.findElement(By.id("searchData"));
        searchBox.sendKeys("strap");

        WebElement searchBtn = driver.findElement(By.className("searchBtn"));
        searchBtn.click();

        // Assertion of the correct result
        WebElement h1Header = driver.findElement(By.className("resultText"));
        System.out.println(h1Header.getText());
        Assertions.assertTrue(h1Header.getText().toLowerCase().contains("strap"));

        // Assertion of the first product name and click it
        WebElement firstItem = driver.findElement(By.cssSelector("div[data-position=\"1\"] .productName"));
        WebElement firstItemPrice = driver.findElement(By.xpath("//div[@data-position=\"1\"]//span[contains(@class, 'newPrice')]"));
        String firstItemText = firstItem.getText();
        String firstItemPriceText = firstItemPrice.getText();

        Assertions.assertTrue(firstItemText.toLowerCase().contains("strap"));
        firstItem.click();

        // Adding to cart and assertions
        WebElement itemName = driver.findElement(By.className("proName"));
        String itemNameText = itemName.getText();
        String itemPriceText = driver.findElement(By.cssSelector("div[class=\"newPrice\"]")).getText();
        Assertions.assertEquals(itemNameText, firstItemText); // product names are the same assertion
        Assertions.assertEquals(itemPriceText,firstItemPriceText); // product prices are the same assertion

        WebElement addtoCartBtn = driver.findElement(By.cssSelector("button[class=\"addBasketUnify\"]"));
        addtoCartBtn.click();
        Thread.sleep(500);

        WebElement addedToCart = driver.findElement(By.cssSelector("div[class=\"text\"]"));
        Assertions.assertTrue(addedToCart.getText().toLowerCase().contains("sepetinize eklendi"));

        // Navigating to the cart and delete product
        Thread.sleep(500);
        WebElement cartBtn = driver.findElement(By.cssSelector("a[title=\"Sepetim\"]"));
        cartBtn.click();

        Thread.sleep(5000); // you need to close KVKK manually here, while app is waiting

        WebElement prodPrice = driver.findElement(By.cssSelector("div[class=\"dtl total\"] .price"));
        String priceOfItemInCart = prodPrice.getText();

        WebElement prodDescription = driver.findElement(By.cssSelector("a[class=\"prodDescription\"]"));
        String textOfItemInCart = prodDescription.getText();

        Assertions.assertTrue(priceOfItemInCart.contains(firstItemPriceText));
        Assertions.assertEquals(firstItemText,textOfItemInCart);

        WebElement scriptElement = driver.findElement(By.id("js-buyBtn"));
        WebElement deleteBtn = driver.findElement(By.cssSelector("span[title=\"Sil\"]"));

        js.executeScript("arguments[0].scrollIntoView(true);", scriptElement); // scroll to the deleteBtn
        Thread.sleep(500);
        deleteBtn.click();
        Thread.sleep(500);

        WebElement deleteBtnConfirm = driver.findElement(By.id("deleteBtnDFLB"));
        deleteBtnConfirm.click();
        Thread.sleep(500);

        js.executeScript("window.scrollTo(0,0);");
        Thread.sleep(500);
        WebElement emptyCart = driver.findElement(By.cssSelector(".cartEmptyText h2"));
        Assertions.assertTrue(emptyCart.getText().toLowerCase().contains("sepetin boş görünüyor"));

    }



}
