package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SaleTest extends BaseTest {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyTheTotalItemsDisplayedOnTheWomensJacketsPage() {
        driver.findElement(By.xpath("//a[@id='ui-id-8']")).click(); //Finding and clicking on Sale link
        driver.findElement(By.linkText("Jackets")).click();// Finding and clicking on Jackets link
        String expectedText = "Jackets"; // Expected text from requirement
        String actualText = driver.findElement(By.xpath("//div[@class='page-title-wrapper']//h1/span[text()='Jackets']")).getText();//Finding text element and getting text value
        Assert.assertEquals("Jackets text is not there.", expectedText, actualText);//Validating expected and actual text
        String expectedTotalItems = driver.findElement(By.id("toolbar-amount")).getText();// Getting the text of number of products displayed
        List<WebElement> jackets = driver.findElements(By.xpath("//div[@class='products wrapper grid products-grid']/ol/li[contains(@class,'item product')]//div[@class='product details product-item-details']//a[@class='product-item-link']"));//Getting list of products displayed
        String actualTotalItems = jackets.size() + " Items";// Getting actual number of products
        for (WebElement a : jackets) { // Iterating the list and printing titles of each element
            System.out.println(a.getText());
            Assert.assertEquals("Total number of items displayed is not matching.", expectedTotalItems, actualTotalItems); // Validating expected and actual text
        }
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
