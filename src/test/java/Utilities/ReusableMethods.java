package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReusableMethods {
    WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(20));
    Actions actions = new Actions(GWD.getDriver());

    // Taking screenshot
    public static String getScreenshot(String name) throws IOException {
        // naming screenshot with date
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        // creating screenshot with selenium using TakeScreenshot
        TakesScreenshot ts = (TakesScreenshot) GWD.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        // path of the screenshot
        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        FileUtils.copyFile(source, finalDestination);

        return target;
    }

    public void hover(WebElement element) {
        Action hover = actions.moveToElement(element).build();
        hover.perform();
    }

    public List<String> listWebToListString(List<WebElement> elementList) {
        List<String> elementTexts = new ArrayList<>();
        for (WebElement abv : elementList) {
            if(!abv.getText().isEmpty()) {
                elementTexts.add(abv.getText());
            }
        }
        return elementTexts;
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void sendKeysFunction(WebElement element, String text) {
        waitUntilVisible(element);
        scrollToElement(element);
        element.clear();
        element.sendKeys(text);
    }

    public void clickFunction(WebElement element) {
        waitUntilClickable(element);
        scrollToElement(element);
        element.click();
    }

    public void verifyContainsTextFunction(WebElement element, String value) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, value));
        Assert.assertTrue(element.getText().toLowerCase().contains(value.toLowerCase()), "no such text as: " + value);
    }

    public void verifyElementDisplayed(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(element.isDisplayed());
    }





}
