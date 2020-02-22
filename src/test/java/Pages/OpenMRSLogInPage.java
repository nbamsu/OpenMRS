package Pages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpenMRSLogInPage {
    public OpenMRSLogInPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(id = "username")
    public WebElement userName;
     @FindBy(id = "password")
    public WebElement password;
      @FindBy(id = "Pharmacy")
    public WebElement pharmacy;
       @FindBy(id = "loginButton")
    public WebElement logIn;

       public void logIn(String userName,String password){
           this.userName.sendKeys(userName);
           this.password.sendKeys(password);
           pharmacy.click();
           logIn.click();
       }
}
