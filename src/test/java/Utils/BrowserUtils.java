package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BrowserUtils {

    public static void switchWindow(String targetTitle){
        WebDriver driver=Driver.getDriver();
       // String mainWindow=driver.getWindowHandle();
        Set<String> allWindows=driver.getWindowHandles();
        if (!driver.getTitle().equals(targetTitle)){
            for (String window:allWindows){
                driver.switchTo().window(window);
                if(driver.getTitle().equals(targetTitle)){
                    break;
                }
            }

        }
    }

   public static void verifyBrokenLink( List<WebElement> links){
        for(WebElement link : links ){
            String hrefValue=link.getAttribute("href");
            try {
                URL url = new URL(hrefValue);
                HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                connect.setConnectTimeout(3000);
                connect.connect();
                if (connect.getResponseCode()>=400 || connect.getResponseCode()>=500) {
                    System.out.println(hrefValue);
                    System.out.println("The link is broken " + connect.getResponseMessage() + " " +
                            connect.getResponseCode());
                }
            }catch (IOException e){
                e.getStackTrace();
            }
        }
    }

    public static void hoverOver ( WebElement element){
        Actions action =new Actions(Driver.getDriver());
        action.moveToElement(element).build().perform();

    }

    public static void dragAndDrop( WebElement from,WebElement to){
        Actions action=new Actions(Driver.getDriver());
        action.dragAndDrop(from,to).build().perform();
    }


    public static WebElement visibility(WebElement element,int seconds){
        WebDriverWait wait=new WebDriverWait(Driver.getDriver(),seconds);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void clickable(WebElement element,int seconds){
        WebDriverWait wait=new WebDriverWait(Driver.getDriver(),seconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void takeScreenShot() throws IOException {
        File scr=((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        File destinationFile=new File("ScreenShotFile\\"+System.currentTimeMillis()+".png");
        FileUtils.copyFile(scr,destinationFile);
    }

    public static WebElement waitForClick(By locator, int timeUnit){
        WebDriverWait wait =new WebDriverWait(Driver.getDriver(),timeUnit);
       return  wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static WebElement waitForVisibility(By locator,int timeUnint){
       WebDriverWait wait=new WebDriverWait(Driver.getDriver(),timeUnint);
       return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebElement fluentWait(WebElement element,int timeUnit,int pollingTime){
        Wait<WebDriver> wait=new FluentWait(Driver.getDriver())
                .withTimeout(timeUnit, TimeUnit.SECONDS)
                .pollingEvery(pollingTime,TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement element1=wait.until(
                driver->element
        );
        return element1;
    }
    //WebElement submitButton=BrowserUtils.fluentWait(page.submitButton,10,2);
    //submitButton.click();

    public static void waitForSec(int time){
        time=time*1000;
        try{
            Thread.sleep(time);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
    public static void selectByName(WebElement element,String text){
        Select select=new Select(element);
        select.selectByVisibleText(text);
    }

}
