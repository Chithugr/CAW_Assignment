package TestNG_CAW;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Open {
    WebDriver driver;
    String jsondata = "[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\":\n" +
            "\"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\":\n" +
            "\"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]";

    //Set up Chrome up
    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Chrome\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    //Open Url
    @Test
    public void TC1() throws InterruptedException {
        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Thread.sleep(5000);
    }

    //Click on table
    @Test
    public void TC2() throws InterruptedException {
        WebElement table = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/details[1]/summary[1]"));
        table.click();
        Thread.sleep(5000);

    }

    //Insert json data to input
    @Test
    public void TC3() throws InterruptedException {
        WebElement input = driver.findElement(By.id("jsondata"));
        if (input.isEnabled()) {
            input.clear();
            input.sendKeys(jsondata);
        }
        Thread.sleep(5000);
        String inputData = input.getAttribute("value");
        Assert.assertEquals(inputData, jsondata, "Input field doesn't contain the expected JSON data");


    }

    //Click on refresh button
    @Test
    public void TC4() throws InterruptedException {
        WebElement refresh = driver.findElement(By.id("refreshtable"));
        refresh.click();
        Thread.sleep(5000);
    }

    //To verify Assertion
    @Test
    public void TC5(){
        WebElement table = driver.findElement(By.id("dynamictable"));
        String tableText = table.getText();

        // Verify that the table contains specific data
        Assert.assertTrue(tableText.contains("Bob"));
        Assert.assertTrue(tableText.contains("20"));
        Assert.assertTrue(tableText.contains("George"));
        Assert.assertTrue(tableText.contains("Sara"));
        Assert.assertTrue(tableText.contains("Conor"));
        Assert.assertTrue(tableText.contains("Jennifer"));

        // Verify that the table doesn't contain unexpected data
        Assert.assertFalse(tableText.contains("RandomText"));
        
    }

    //To close browser
    @AfterTest
    public void teardown() {
        driver.quit();
    }

}
