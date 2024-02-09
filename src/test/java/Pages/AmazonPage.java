package Pages;

import Utilities.GWD;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AmazonPage {

    public AmazonPage() {
        PageFactory.initElements(GWD.getDriver(), this);
    }

    @FindBy(id = "sp-cc-rejectall-link")
    public WebElement rejectCookies;

    @FindBy(id = "sp-cc-accept")
    public WebElement acceptCookies;

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    public WebElement searchBtn;

    @FindBy(css = "div[data-index=\"3\"] h2 span")
    public WebElement firstItemAfterSearch;

    @FindBy(css = "div[data-index=\"3\"] span[class=\"a-price-whole\"]")
    public WebElement firstItemPricePt1;

    @FindBy(css = "div[data-index=\"3\"] span[class=\"a-price-fraction\"]")
    public WebElement firstItemPricePt2;

    @FindBy(css = "span[class=\"a-size-base-plus a-color-base a-text-normal\"]")
    public List<WebElement> productTitles;

    @FindBy(id = "nav-link-accountList")
    public WebElement loginNavBtn;

    @FindBy(id = "ap_email")
    public WebElement inputMail;

    @FindBy(id = "ap_password")
    public WebElement inputPassword;

    @FindBy(id = "continue")
    public WebElement continueBtn;

    @FindBy(id = "signInSubmit")
    public WebElement loginBtn;

    @FindBy(css = "div h4")
    public WebElement alertBox;


}
