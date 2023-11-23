package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {

        driver.findElement(By.xpath("//li[@class='authorization-link']")).click();// Finding sign in link and clicking on it
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("primekaku@gmail.com"); // finding email field and passing value
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Primekaku5700"); // finding password field and passing value
        driver.findElement(By.xpath("//button[@class='action login primary']")).click(); // Finding Login and clicking on it
        String expectedText = "Welcome"; // Expected text from requirement
        String actualText = driver.findElement(By.xpath("//div[@class='panel header']//li[@class='greet welcome']/span[contains(text(),'Welcome')]")).getText().substring(0, 7);//Finding text element and getting the text trimming unwanted part
        Assert.assertEquals("User was not logged in successfully.", expectedText, actualText);// Validating expected and actual message
    }

    @Test
    public void verifyTheErrorMessageWithInvalidCredentials() {

        driver.findElement(By.xpath("//li[@class='authorization-link']")).click();// Finding sign in link and clicking on it
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("saree@gmail.com"); // finding email field and passing invalid value
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Abcd1234"); // finding password field and passing invalid value
        driver.findElement(By.xpath("//button[@class='action login primary']")).click(); // Finding Login and clicking on it
        String expectedError = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        String actualError = driver.findElement(By.xpath("//div[contains(text(),'The account sign-in was incorrect or your account ')]")).getText();//Finding text element and getting the text
        Assert.assertEquals("Error message was not verified.", expectedError, actualError);// Validating expected and actual error messages
    }

    @Test
    public void userShouldLogOutSuccessfully() {
        driver.findElement(By.xpath("//li[@class='authorization-link']")).click();// Finding sign in link and clicking on it
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("primekaku@gmail.com"); // finding email field and passing value
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Primekaku5700"); // finding password field and passing value
        driver.findElement(By.xpath("//button[@class='action login primary']")).click(); // Finding Login and clicking on it
        String expectedText = "Welcome"; // Expected text from requirement
        String actualText = driver.findElement(By.xpath("//div[@class='panel header']//li[@class='greet welcome']/span[contains(text(),'Welcome')]")).getText().substring(0, 7);//Finding text element and getting the text trimming unwanted part
        driver.findElement(By.xpath("//button[@class='action switch']")).click();// Finding drop down arrow icon next to username and clicking on it
        driver.findElement(By.xpath("//li[@class='authorization-link']")).click();// Finding and clicking on Sign out option
        String expectedMessage1 = "You are signed out";// Expected message from requirements
        String actualMessage1 = driver.findElement(By.xpath("//span[contains(text(),'You are signed out')]")).getText();// Finding text element and getting text value
        Assert.assertEquals("User was unable to sign out.", expectedMessage1, actualMessage1);// Validating expected and actual message
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}

