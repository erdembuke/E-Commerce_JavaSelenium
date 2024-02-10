package _testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonTest {
    /*
        Step 1: ChromeDriver'in yerini belirtiyoruz ve WebDriver'i baslatiyoruz
        Step 2: Amazon Web sitesine gidiyoruz
        Step 3: Arama kutusunu buluyoruz ve içine "laptop" kelimesini yazıyoruz
        Step 4: Arama butonunu buluyoruz ve tıklıyoruz
        Step 5: İlk ürünün adını ve fiyatını alıyoruz
        Step 6: Ürün adını ve fiyatını konsola yazdırıyoruz
        Step 7: Ürünü sepete ekliyoruz
        Step 8: Urunun adinin sepete dogru bir sekilde eklendigini kontrol et
        Step 9: Urunu sepetten kaldir
        Step 10: Sepetin bos oldugunu kontrol et
        Step 11: Anasayfaya don
        Step 12: WebDriver'i kapatıyoruz
        Assertion 1: Arama kutusunun doğru bir şekilde bulunduğunu kontrol ediyoruz.
        Assertion 2: Arama butonunun doğru bir şekilde bulunduğunu kontrol ediyoruz.
        Assertion 3: Ürün adının boş olmadığını kontrol ediyoruz
        Assertion 4: Ürün fiyatının boş olmadığını kontrol ediyoruz
         */
    private WebDriver driver;
    @BeforeTest
    public void setup() {
        // Step 1
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // fullscreen

        // Step 2
        driver.get("https://www.amazon.com.tr");
    }

    @AfterTest
    public void tearDown() {
        // Step 12
        driver.quit();
    }


    @Test
    public void e2eTest() throws InterruptedException {

        WebElement cookies = driver.findElement(By.id("sp-cc-accept")); // accepting cookies
        cookies.click();

        // Step 3
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox")); // XPath: //input[@id='twotabsearchtextbox]
        searchBox.sendKeys("laptop");

        // Step 4
        WebElement searchBtn = driver.findElement(By.id("nav-search-submit-button"));
        searchBtn.click();

        // Step 5 - Step 6
        WebElement firstLaptop = driver.findElement(By.cssSelector("div[data-index=\"3\"] h2 span"));
        String firstLaptopText = firstLaptop.getText();
        System.out.println("Item: " + firstLaptopText);

        String price = "";

        WebElement priceWhole = driver.findElement(By.xpath("//div[@data-index=\"3\"]//span[@class=\"a-price-whole\"]"));
        WebElement priceFraction = driver.findElement(By.xpath("//div[@data-index=\"3\"]//span[@class=\"a-price-fraction\"]"));

        price = "Price: " + priceWhole.getText() + "," + priceFraction.getText() + " TL";
        System.out.println(price);

        // Step 7
        firstLaptop.click();

        WebElement addToCartBtn = driver.findElement(By.id("add-to-cart-button"));
        addToCartBtn.click();

        // Step 8
        WebElement cartBtn = driver.findElement(By.id("nav-cart"));
        cartBtn.click();
        Thread.sleep(2000);

        WebElement cartTitle = driver.findElement(By.cssSelector("#sc-active-cart h1"));
        String expectedTitle = "Alışveriş Sepeti";
        Assert.assertTrue(cartTitle.getText().contains(expectedTitle));

        WebElement itemText = driver.findElement(By.className("a-truncate-cut"));
        String itemTextInCart = itemText.getText().substring(0,itemText.getText().length()-3); // deletes the ... part at the end
        Assert.assertTrue(firstLaptopText.contains(itemTextInCart));

        // Step 9
        WebElement deleteInCart = driver.findElement(By.cssSelector("input[value=\"Sil\"]"));
        deleteInCart.click();

        // Step 10
        Thread.sleep(1000);
        String expectedTitle2 = "Amazon sepetiniz boş";
        String cartDeletedText = driver.findElement(By.xpath
                ("//h1[contains(text(), \"Amazon sepetiniz boş.\")]")).getText(); // for avoiding stale element error
        Assert.assertTrue(cartDeletedText.contains(expectedTitle2));

        // Step 11
        WebElement mainPageBtn = driver.findElement(By.id("nav-logo-sprites"));
        mainPageBtn.click();

    }

}
