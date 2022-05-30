package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver(){}

    private static WebDriver driver;

    public static WebDriver getDriver(){
        if(driver==null){

            String browserType=ConfigurationReader.getProperty("browser");

            if(browserType.equals("chrome")){

                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();
            }
            if (browserType.equals("firefox")){WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();}



        }

        return driver;


    }







    
}
