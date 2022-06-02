package oscarsTasks;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import utilities.Driver;

public class Task1 {


    @Test

    public void test1(){

        Driver.getDriver().get("https://www.amazon.com");
       WebElement searchBox= Driver.getDriver().findElement(By.xpath("(//div[@class='nav-fill'])[2]//input"));

      searchBox.sendKeys(ConfigurationReader.getProperty("searchValue")+Keys.ENTER);

      WebElement firsthat=Driver.getDriver().findElement(By.xpath("//div[@data-cel-widget='search_result_0']/following-sibling::div[1]"));


      firsthat.click();
   // String onePrice=  Driver.getDriver().findElement(By.xpath("(//span[@aria-hidden='true'])[14]")).getText();

       Driver.getDriver().findElement(By.xpath("//span[@class='a-button-text a-declarative']")).click();
       Driver.getDriver().findElement(By.xpath("//a[@data-value='{\"stringVal\":\"2\"}']")).click();
       Driver.getDriver().findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
       Driver.getDriver().findElement(By.linkText("Go to Cart")).click();

       String actuallresult=   Driver.getDriver().findElement(By.xpath("//span[@class='a-dropdown-prompt']")).getText();
        Assert.assertTrue(actuallresult.equals("2"));

        String actualPrice=Driver.getDriver().findElement(By.xpath("//span[@class='a-offscreen']")).getText();

        Assert.assertEquals(68.00,actualPrice);
        // Assert.assertTrue(actuallresult.equals("$68.00"));



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