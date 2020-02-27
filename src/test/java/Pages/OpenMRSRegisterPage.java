package Pages;

import Utils.BrowserUtils;
import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class OpenMRSRegisterPage {
    public OpenMRSRegisterPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//h2")
    public WebElement registerText;
    @FindBy(xpath = "//fieldset[@id='demographics-name']//h3")
    public WebElement patientNameQuestion;
    @FindBy(xpath = "//fieldset[@id='demographics-name']//p//label")
    public List<WebElement> givenMiddleFamilyHeaders;
    @FindBy(xpath = "//ul[@id='formBreadcrumb']//span")
    public List<WebElement> demographicsName;
    @FindBy(name="givenName")
    public WebElement givenName;
    @FindBy(name="familyName")
    public WebElement familyName;
    @FindBy(xpath = "//span[@id='genderLabel']")
    public WebElement genderLabel;
    @FindBy(id="gender-field")
    public WebElement genderField;
    @FindBy(xpath = "//span[@id='birthdateLabel']")
    public WebElement birthdateLevel;
    @FindBy(id="birthdateDay-field")
    public WebElement birthdayfield;
    @FindBy(id = "birthdateMonth-field")
    public WebElement monthField;
    @FindBy(id = "birthdateYear-field")
    public WebElement birthdayYear;

    @FindBy(xpath = "//span[.='Address']")
    public WebElement addressButton;
    @FindBy(id = "address1")
    public WebElement address;
    @FindBy(name = "phoneNumber")
    public WebElement phoneNumber;

    @FindBy(xpath = "//span[.='Phone Number']")
    public WebElement phoneNumberBox;

    @FindBy(xpath = "//span[.='Relatives']")
    public WebElement realtives;

    @FindBy(id = "relationship_type")
   public WebElement ralationship;

    @FindBy(id = "relationship_type")
    public WebElement relationshipTYPE;
    @FindBy(xpath = "//input[@ng-model='relationship.name']")
    public WebElement relationshipName;
    @FindBy(id = "confirmation_label")
    public WebElement confirmationLabel;
    @FindBy(xpath = "//div[@id='dataCanvas']//p")
    public List<WebElement> confirmationList;
    @FindBy(id="submit")
    public WebElement submitButton;
    @FindBy(className = "//ul[@id='breadcrumbs']//a")
    public WebElement homeButton;



    public static List<String>getData(){
        List<String> headers=new ArrayList<String>();
        headers.add("Demographics");
        headers.add("Name");
        headers.add("Gender");
        headers.add("Birthday");
        headers.add("Contact Info");
        headers.add("Address");
        headers.add("Phone Number");
        headers.add("Relationships");
        headers.add("Relatives");
        headers.add("Confirm");
        return headers;
    }
    public static List<String> getConfirmationData(){
        List<String> header=new ArrayList<>();
        header.add("Name: Adam, Lee");
        header.add("Gender: Male");
        header.add("Birthday: 22, May, 2002");
        header.add("Address: Irving park");
        header.add("Phone Number: 9379992");
        header.add("Relatives: Dudu - Child");
        return header;
    }
    public void registerPatient(String name, String familyName,String gender,String day,String month, String year,String address,
                                        String phoneNumber,String relationship,String relationshipName2){
        givenName.sendKeys(name);
        this.familyName.sendKeys(familyName);
        genderLabel.click();
        BrowserUtils.selectByName(genderField,gender);
        birthdateLevel.click();
        birthdayfield.sendKeys(day);
        BrowserUtils.selectByName(monthField,month);
        birthdayYear.sendKeys(year);
        addressButton.click();
        this.address.sendKeys(address);
        phoneNumberBox.click();
        this.phoneNumber.sendKeys(phoneNumber);
        realtives.click();
        BrowserUtils.selectByName(relationshipTYPE,relationship);
        relationshipName.sendKeys(relationshipName2);
        confirmationLabel.click();

    }
}
