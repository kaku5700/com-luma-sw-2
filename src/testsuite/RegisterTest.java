package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.Random;

public class RegisterTest extends BaseTest {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyThatSignInPageDisplay() {
        //click on create an account button
        driver.findElement(By.xpath("(//a[contains(text(),'Create an Account')])[1]")).click();
        //verify text message
        String expectedMessage = "Create New Customer Account";
        String actualMessage = driver.findElement(By.xpath("//span[contains(text(),'Create New Customer Account')]")).getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    public static String getRandomEmail() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder random = new StringBuilder();
        Random rnd = new Random();
        while (random.length() < 10) {
            int index = (int) (rnd.nextFloat() * chars.length());
            random.append(chars.charAt(index));
        }
        String email = random.toString() + "@gmail.com";
        return email;
    }

    @Test
    public void userShouldRegisterAccountSuccessfully() {

        driver.findElement(By.xpath("//a[text()='Create an Account']")).click();
        // Finding First name field and passing value
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Jaynika");
        // Finding last name field and passing value
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Patel");
        // Finding email field and passing value
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(getRandomEmail());
        // Finding password field and sending value
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Abcd1234");
        driver.findElement(By.xpath("//input[@id='password-confirmation']")).sendKeys("Abcd1234");// Finding confirm password field and sending value
        driver.findElement(By.xpath("//div[@class='primary']")).click();//Finding create and account button and clicking on it
        String expectedMessage = "Thank you for registering with Main Website Store.";// Expected message from requirement
        String actualMessage = driver.findElement(By.xpath("//div[contains(text(),'Thank you for registering with Main Website Store.')]")).getText(); // Finding text element and getting text value
        Assert.assertEquals("User was unable to register successfully", expectedMessage, actualMessage);// Validating expected and actual message
        driver.findElement(By.xpath("//button[@class='action switch']")).click();// Finding drop down arrow icon next to username and clicking on it
        driver.findElement(By.xpath("//li[@class='authorization-link']")).click();// Finding and clicking on Sign out option
        String expectedMessage1 = "You are signed out";// Expected message from requirements
        String actualMessage1 = driver.findElement(By.xpath("//span[contains(text(),'You are signed out')]")).getText();// Finding text element and getting text value
        Assert.assertEquals("User was unable to sign out.", expectedMessage1, actualMessage1);// Validating expected and actual message
    }

    @After //JUnit Annotation which will run after every Method
    public void tearDown() {
        closeBrowser();
    }

}
