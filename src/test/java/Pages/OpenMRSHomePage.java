package Pages;

import Utils.Driver;
import org.openqa.selenium.support.PageFactory;

public class OpenMRSHomePage {
    public OpenMRSHomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

}
