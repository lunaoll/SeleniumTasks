package oscarsTasks;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task1 {
    WebDriver driver;

    @BeforeMethod
    public void setupMethod() {

        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.amazon.com");
    }

    @Test
    public void test1() {

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));

        searchBox.sendKeys(ConfigurationReader.getProperty("searchValue") + Keys.ENTER);

        //get the list of all hats
        List<WebElement> hats = driver.findElements(By.cssSelector(".s-main-slot.s-result-list.s-search-results.sg-row>div[data-component-type='s-search-result']"));

        //click on first hat
        hats.get(0).click();

        String actualUnitPrice = driver.findElement(By.xpath("//span[@class='a-offscreen']/parent::span[@class]/parent::td[@class='a-span12']")).getText();
        //System.out.println("actualUnitPrice = " + actualUnitPrice);

        //choose product quantity (2)
        Select quantityBtn = new Select(driver.findElement(By.id("quantity")));
        quantityBtn.selectByValue("2");

        //add to cart
        driver.findElement(By.id("add-to-cart-button")).click();

        //open Cart
        driver.findElement(By.linkText("Go to Cart")).click();

        //convert string unitPrice to double
        double unitPrice = Double.parseDouble(actualUnitPrice.substring(1));
        System.out.println("unitPrice = " + unitPrice);

        String actualTotalPrice = driver.findElement(By.xpath("(//div[@data-name='Subtotals'])[2]")).getText(); //Subtotal (2 items): $39.98
        System.out.println("actualTotalPrice = " + actualTotalPrice);


        String newStr = splitAndExtractPrice(actualTotalPrice);

        //convert newStr(total price) into double
        double totalPrice = Double.parseDouble(newStr);

        System.out.println("actualTotalPrice = " + totalPrice);

        Assert.assertEquals(totalPrice, unitPrice*2);

        String actualQuantity = driver.findElement(By.xpath("//span[@class='a-dropdown-prompt']")).getText();
        Assert.assertEquals(actualQuantity, "2");

        driver.navigate().refresh();

        //fix exception with try−catch block
        try{
            quantityBtn.selectByValue("1");
        }
        catch(StaleElementReferenceException e){
            quantityBtn = new Select(Driver.getDriver().findElement(By.id("quantity")));
            quantityBtn.selectByValue("1");
        }

        System.out.println("======================AFTER DECREASING THE QUANTITY======================");
        driver.navigate().refresh();

        String actualTotalPriceAfterDecrease = driver.findElement(By.xpath("(//div[@data-name='Subtotals'])[2]")).getText();
        System.out.println("actualTotalPrice = " + actualTotalPriceAfterDecrease);


        newStr = splitAndExtractPrice(actualTotalPriceAfterDecrease);

        totalPrice = Double.parseDouble(newStr);

        System.out.println("actualTotalPrice = " + totalPrice);

        Assert.assertEquals(totalPrice, unitPrice);

        actualQuantity = driver.findElement(By.xpath("//span[@class='a-dropdown-prompt']")).getText();
        Assert.assertEquals(actualQuantity, "1");
    }

    public String splitAndExtractPrice(String str) {

        //split actualTotalPrice according to : to extract price as string
        String[] splitted = str.split(":");

        //remove redundant spaces
        splitted[1] = splitted[1].trim();
        System.out.println("splitted[1] = " + splitted[1]);

        String newStr = "";

        //get the price char by char without $ sign and store it in newStr
        for (int i=0 ; i<splitted[1].length() ; i++) {
            if (splitted[1].charAt(i)>='0' && splitted[1].charAt(i)<='9' || splitted[1].charAt(i)=='\n' || splitted[1].charAt(i)=='.') {
                if (splitted[1].charAt(i)=='\n')
                    newStr += '.';
                else
                    newStr += splitted[1].charAt(i);
            }
        }
        System.out.println("newStr = " + newStr);
        return newStr;
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

 /*   For the Tasks bellow create a new Maven Project, use the latest topics you have learned, like Page Object Modeling, singleton Driver class.
        Share your project on GitHub, create a commit history. Also for each different task create a branch, then merge it when the task is over. (Optional; share you project Github Link at Tasks and Solutions Channel on Discord.)

        Task 1: The task consists on the next steps:
        1.	Go to https://www.amazon.com
        2.	Search for "hats for men" (Call from Configuration.properties file)
        3.	Add the first hat appearing to Cart with quantity 2
        4.	Open cart and assert that the total price and quantity are correct
        5.	Reduce the quantity from 2 to 1 in Cart for the item selected in the step 3
        6.	Assert that the total price and quantity has been correctly changed
        The goal of this test is to check if you are able to automate a test of a given website, but we'd like you to also demonstrate the coding quality, structure, and style of the deliverables.
*/