package Tests.com.openmrs.LogInTests;

import Pages.OpenMRSLogInPage;
import Tests.TestBase;
import Utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenMRSLoginTest extends TestBase {
    @Test
    public void testLogIn(){
        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        OpenMRSLogInPage page=new OpenMRSLogInPage();
        String userName= ConfigReader.getProperty("userName");
        String password=ConfigReader.getProperty("password");
        page.logIn(userName, password);
        String expectedTitle="Home";
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
    }
}
