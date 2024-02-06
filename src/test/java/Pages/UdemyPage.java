package Pages;

import Utilities.GWD;
import org.openqa.selenium.support.PageFactory;

public class UdemyPage {

    public UdemyPage() {
        PageFactory.initElements(GWD.getDriver(), this);
    }

}
