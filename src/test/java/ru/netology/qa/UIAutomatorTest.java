package ru.netology.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.AUTOMATION_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;
import static ru.netology.qa.MobileObjects.*;

public class UIAutomatorTest {
    private AppiumDriver driver;
    public MobileObjects mobileObjects;
    enum Platform {iOS, Android}
    private static final Platform platform = Platform.Android;
    private static final String textToSet = "Netology";
    private static final String emptyTextToSet = "   ";

    @BeforeEach
    public void createDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        URL remoteUrl = (new URL("http://127.0.0.1:4723"));
        if (platform == Platform.Android) {
            desiredCapabilities.setCapability(PLATFORM_NAME, "android");
            desiredCapabilities.setCapability(DEVICE_NAME, "huawei");
            desiredCapabilities.setCapability(APP_PACKAGE, "ru.netology.testing.uiautomator");
            desiredCapabilities.setCapability(APP_ACTIVITY, "ru.netology.testing.uiautomator.MainActivity");
            desiredCapabilities.setCapability(AUTOMATION_NAME, "UiAutomator2");
            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
//        } else if (platform == Platform.iOS) {
//            desiredCapabilities.setCapability(PLATFORM_NAME, "iOS");
//            desiredCapabilities.setCapability(DEVICE_NAME, "iPhone 11");
//            desiredCapabilities.setCapability(BUNDLE_ID, "ru.netology.testing.uiautomator");
//            desiredCapabilities.setCapability(AUTOMATION_NAME, "XCUITest");
//            driver = new IOSDriver(remoteUrl, desiredCapabilities);
        } else {
            throw new IllegalArgumentException(String.format("Платформа %s не поддерживается", platform));
        }
        mobileObjects = new MobileObjects(driver);
    }

    @Test
    public void testOpenTextInNewActivity() {
        userInput.click();
        userInput.sendKeys(textToSet);
        buttonActivity.click();
        mobileObjects.waitForTextInNewActivity();
        var actual = textInNewActivity.getText();
        Assertions.assertEquals(textToSet, actual);
    }

    @Test
    public void testNotChangingTextWhenEnteringSpaces() {
        var expected = textToBeChanged.getText();
        userInput.click();
        userInput.sendKeys(emptyTextToSet);
        buttonChange.click();
        var actual = textToBeChanged.getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testNotChangingTextWithEmptyValue() {
        var expected = textToBeChanged.getText();
        buttonChange.click();
        var actual = textToBeChanged.getText();
        Assertions.assertEquals(expected, actual);
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
