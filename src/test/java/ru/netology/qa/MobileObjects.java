package ru.netology.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MobileObjects {
    public static WebElement userInput;
    public static WebElement buttonChange;
    public static WebElement textToBeChanged;
    public static WebElement buttonActivity;
    public static WebElement textInNewActivity;
    public AppiumDriver driver;

    public MobileObjects(AppiumDriver driver) {
        this.driver = driver;
        if (driver instanceof AndroidDriver) {
            userInput = driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
            buttonChange = driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonChange"));
            textToBeChanged = driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
            buttonActivity = driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonActivity"));
        }
    }

    public void waitForTextInNewActivity() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        textInNewActivity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ru.netology.testing.uiautomator:id/text")));
    }
}


