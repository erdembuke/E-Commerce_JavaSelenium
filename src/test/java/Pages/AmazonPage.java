package Pages;

import Utilities.GWD;
import org.openqa.selenium.support.PageFactory;

public class AmazonPage {

    public AmazonPage() {
        PageFactory.initElements(GWD.getDriver(), this);
    }




}
