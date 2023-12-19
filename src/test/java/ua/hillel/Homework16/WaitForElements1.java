package ua.hillel.Homework16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.NoSuchElementException;


public class WaitForElements1 {
    private static WebDriver driver;

    @BeforeClass
    static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading");
    }

    @AfterClass
    static void tearDown() {
        driver.quit();
    }

    @Test
    public void waitHelloWorld() {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        WebElement waitHW = driver.findElement(By.linkText("Example 1: Element on page that is hidden"));
        waitHW.click();

        WebElement clickStart = driver.findElement(By.xpath("//button[text()='Start']"));
        clickStart.click();

        WebElement text = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        Assert.assertEquals(text.getText(), "Hello World!");

    }

    @Test
    public void waitHelloWorldTwo() {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        String url = "https://the-internet.herokuapp.com/dynamic_loading";
        driver.get(url);

        WebElement waitHW = driver.findElement(By.linkText("Example 2: Element rendered after the fact"));
        waitHW.click();

        WebElement clickStart = driver.findElement(By.xpath("//button[text()='Start']"));
        clickStart.click();

        WebElement text = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        Assert.assertEquals(text.getText(), "Hello World!");
    }
}