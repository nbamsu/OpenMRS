package Tests.com.openmrs.Hometests;

import Pages.OpenMRSHomePage;
import Pages.OpenMRSLogInPage;
import Tests.TestBase;
import Utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OpenMRSHomePageTest extends TestBase {
   OpenMRSHomePage page=new OpenMRSHomePage();
    @BeforeClass
    public void loginMRS(){
        OpenMRSLogInPage logInPage=new OpenMRSLogInPage();
        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        logInPage.logIn(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password"));
    }

    @Test
    public void validateMessage(){
       OpenMRSHomePage page=new OpenMRSHomePage();
        String expected="Logged in as Super User (admin) at Pharmacy.";
        String actualMessage=page.textMessage.getText();
        Assert.assertEquals(actualMessage,expected);
    }
    @Test
    public void validateURL(){
        String expected="https://demo.openmrs.org/openmrs/referenceapplication/home.page";
        String actualURL=driver.getCurrentUrl();
        Assert.assertEquals(actualURL,expected);
    }
    @Test
    public void validateHomeApps(){
        for (WebElement elements:page.homePageApps){
            Assert.assertTrue(elements.isDisplayed());
        }


    }

}
