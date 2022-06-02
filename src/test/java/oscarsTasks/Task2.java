package oscarsTasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import utilities.Driver;

public class Task2 {

    @Test
    public void test2(){
        Driver.getDriver().get("https://moneygaming.qa.gameaccount.com/");
        Driver.getDriver().findElement(By.xpath("//a[@href='/sign-up.shtml']")).click();

    WebElement titleSection= Driver.getDriver().findElement(By.xpath("//select[@class='title required']"));
        Select select=new Select(titleSection);
        select.selectByValue("Mrs");


        WebElement userName=Driver.getDriver().findElement(By.xpath("//input[@id='forename']"));
        userName.sendKeys("Zeliha");

        Driver.getDriver().findElement(By.xpath("//input[@name='map(lastName)']")).sendKeys("Yildiz");
        Driver.getDriver().findElement(By.xpath("//input[@id='checkbox']")).click();






    }







}

/*
Task 2:
        1. Navigate to: https://moneygaming.qa.gameaccount.com/
        2. Click the JOIN NOW button to open the registration page
        3. Select a title value from the dropdown
        4. Enter your first name and surname in the form
        5. Check the tick box with text 'I accept the Terms and Conditions and certify that I am over
        the age of 18.'
        6. Submit the form by clicking the JOIN NOW button
        7. Validate that a validation message with text ‘ This field is required’ appears under the date of
        birth box
*/