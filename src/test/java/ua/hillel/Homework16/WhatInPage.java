package ua.hillel.Homework16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class WhatInPage {
    private static WebDriver driver;

    @BeforeClass
    static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");
    }

    @AfterClass
    static void tearDown() {
        driver.quit();
    }

    @Test
    public void checkFirstButton() {
        WebElement clickABox = driver.findElement(By.id("adder"));
        clickABox.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement findSquare = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box0")));
        String actualColor = findSquare.getCssValue("color");
        String expectedColor = "rgba(0, 0, 0, 1)";
        Assert.assertEquals(expectedColor, actualColor);
    }

    @Test
    public void checkSecondButton() {
        WebElement clickReveal = driver.findElement(By.id("reveal"));
        clickReveal.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement findInputField = wait.until(ExpectedConditions.elementToBeClickable(By.id("revealed")));
        findInputField.sendKeys("I want to sleep!");
    }
}
