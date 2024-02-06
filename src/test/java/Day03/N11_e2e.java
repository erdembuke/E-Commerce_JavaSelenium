package Day03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class N11_e2e {
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
    public void endMethod() {
        // closing the driver after each test
        driver.quit();
    }

    @Test
    public void addProductTest() {

    }


}
