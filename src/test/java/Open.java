import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
public class Open {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Chrome\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement table=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/details[1]/summary[1]"));
        table.click();

        Thread.sleep(2000);

        WebElement input=driver.findElement(By.id("jsondata"));
        String jsondata="[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\":\n" +
                "\"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\":\n" +
                "\"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]";
        if (input.isEnabled()) {
            input.clear();
            input.sendKeys(jsondata);
        }
        Thread.sleep(2000);
        driver.findElement(By.id("refreshtable")).click();

        Thread.sleep(2000);


    }
}