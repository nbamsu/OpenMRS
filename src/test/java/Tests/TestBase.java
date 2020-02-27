package Tests;

import Utils.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class TestBase {
    public static SoftAssert soft;
    public static WebDriver driver;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;


    @BeforeTest
    public void setDriver(){
        driver= Driver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        soft=new SoftAssert();

        htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-output\\myReport.html");
        htmlReporter.config().setDocumentTitle("OpenMRS Automation report");
        htmlReporter.config().setReportName("Registration Functionality Validation");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports=new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("OS","MAC OS");
        extentReports.setSystemInfo("Browser","Chrome");
        extentReports.setSystemInfo("Environment","Test Environment");
        extentReports.setSystemInfo("Domain","https://demo.openmrs.org/");


    }
    @AfterTest
    public void tearDown(){
        if (driver!=null){
            extentReports.flush();
            //driver.quit();
        }
    }

}
