package Tests.com.openmrs.RegisterTests;

import Pages.OpenMRSHomePage;
import Pages.OpenMRSLogInPage;
import Pages.OpenMRSRegisterPage;
import Tests.TestBase;
import Utils.BrowserUtils;
import Utils.ConfigReader;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterPageTest extends TestBase {

    OpenMRSHomePage homePage=new OpenMRSHomePage();
    OpenMRSRegisterPage registerPage=new OpenMRSRegisterPage();

    @DataProvider(name = "PatientInfo")
    public Object[][] getPatientinfo() {
        return new Object[][]{{"Jason", "Mason", "Male", "15", "April", "2001", "2200 Devon ave", "3122121", "Child", "Robert"},
                {"Maria", "Garcia", "Female", "22", "October", "1998", "501 East mall dr", "212312", "Sibling", "Marai"},
                {"Jmaes", "Anderson", "Male", "23", "December", "1988", "33 e Wacker dr", "44234232", "Supervisor", "Jenifer"}};

    }
    @BeforeClass
    public void loginMRS(){
        OpenMRSLogInPage logInPage=new OpenMRSLogInPage();
        driver.get("https://demo.openmrs.org/openmrs/login.htm");
        logInPage.logIn(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password"));
        homePage.clickApp("Register a patient");
    }

    @Test
    public void validateTitle(){
        extentTest=extentReports.createTest("This is for Title validation");
        String expectedTitle="OpenMRS Electronic Medical Record";
        String actualTitle=driver.getTitle();
        extentTest.log(Status.INFO,"This is before Title validation");
        //Assert.assertEquals(actualTitle,expectedTitle);
        soft.assertEquals(actualTitle,expectedTitle);
        extentTest.log(Status.INFO,"Title is validated");
    }
    @Test
    public void validateHeaders(){
        extentTest=extentReports.createTest("This is for Header validation");
        String actualRegisterText=registerPage.registerText.getText();
        String expectedRegisterText="Register a patient";
        //Assert.assertEquals(actualRegisterText,expectedRegisterText);
        soft.assertEquals(actualRegisterText,expectedRegisterText);
        extentTest.log(Status.INFO,"Register text is validatd");

        String actualQuestionText=registerPage.patientNameQuestion.getText();
        String expectedQuestionTest="What's the patient's name?";
        //Assert.assertEquals(actualQuestionText,expectedQuestionTest);
        soft.assertEquals(actualQuestionText,expectedQuestionTest);
        extentTest.log(Status.INFO,"Question is validated");

        soft.assertEquals(registerPage.givenMiddleFamilyHeaders.get(0).getText(),"Given (required)");
        soft.assertEquals(registerPage.givenMiddleFamilyHeaders.get(1).getText(),"Middle");
        soft.assertEquals(registerPage.givenMiddleFamilyHeaders.get(2).getText(),"Family Name (required)");
        extentTest.log(Status.INFO,"Name headers are Validated");
        //soft.assertAll();


    }

    @Test
    public void validateDemographics(){
        extentTest=extentReports.createTest("validate of the demographics");
     List<String> expectedHeader=OpenMRSRegisterPage.getData(); //its coming API response or DataBase
        for (int i=0;i<expectedHeader.size();i++){
            String actualHeader=registerPage.demographicsName.get(i).getText();
            soft.assertEquals(registerPage.demographicsName.get(i).getText(), expectedHeader.get(i));
        }
        extentTest.log(Status.INFO,"Demographics header is validated");
        //soft.assertAll();
    }
    @Test(dependsOnMethods = "validateDemographics")
    public void validatePatientRegistration(){
        extentTest=extentReports.createTest("Single header validation");
     registerPage.registerPatient("Adem","Lee","Male","22","May",
        "2002","2200 Devon ave","9379992","Child","Jackson");
        extentTest.log(Status.INFO,"Patient register information filled");

        List<String> expectedConfirmation=OpenMRSRegisterPage.getConfirmationData();
        for (int i=0;i<expectedConfirmation.size();i++){
            String actualData=registerPage.confirmationList.get(i).getText();
            soft.assertEquals(actualData,expectedConfirmation.get(i));
        }
        extentTest.log(Status.INFO,"Registration validated");

        //soft.assertAll();
        registerPage.submitButton.click();
        extentTest.log(Status.INFO,"Registration confirm button clicked");


    }
    @Test(dataProvider = "PatientInfo",dependsOnMethods = "validatePatientRegistration")
    public void validateMultiplePatient(String name, String familyName,String gender,String day,String month, String year,String address,
                                        String phoneNumber,String relationship,String relationshipName){
        //BrowserUtils.waitForSec(2);
        //registerPage.homeButton.click();
        extentTest=extentReports.createTest("Validating multiple reports ");
        driver.get("https://demo.openmrs.org/openmrs/index.htm");
        homePage.clickApp("Register a patient");
        extentTest.log(Status.INFO,"register a patient button is clicked");
        registerPage.registerPatient(name,familyName,gender,day,month,year,address,phoneNumber,relationship,relationshipName);
        extentTest.log(Status.INFO,"Patient button is clicked");
         registerPage.submitButton.click();
         extentTest.log(Status.INFO,"Registration confirmed");

    }
    @AfterMethod
    public void TakeScrenShot(ITestResult result) throws IOException {
        if (result.getStatus()==ITestResult.FAILURE){
            extentTest.log(Status.FAIL,"Test execution is failed "+result.getName());
            extentTest.log(Status.FAIL,"The name of the error "+result.getThrowable());
            BrowserUtils.takeScreenShot();
        }
    }

}
