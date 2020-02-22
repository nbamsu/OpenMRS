package Tests;

import Utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    @BeforeTest
    public void setDriver(){
        driver= Driver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


    }
    @AfterTest
    public void tearDown(){
        if (driver!=null){
            driver.quit();
        }
    }

}
