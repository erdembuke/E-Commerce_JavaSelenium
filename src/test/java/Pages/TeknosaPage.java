package Pages;

import Utilities.GWD;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeknosaPage extends Parent {

    public TeknosaPage() {
        PageFactory.initElements(GWD.getDriver(), this);
    }

    @FindBy(className = "ins-web-opt-in-reminder-close-button")
    public WebElement closeBtnMainPage;

    @FindBy(css = "a[class=\"btn-user\"]")
    public WebElement navLoginBtn;

    @FindBy(css = ".sbx-input button")
    public WebElement searchBox;

    @FindBy(id = "search-input")
    public WebElement searchBox2;

    @FindBy(css = "button[aria-label=\"Kelime Ara\"]")
    public WebElement searchBtn;
}
