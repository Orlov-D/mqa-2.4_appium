package ru.netology.qa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class ForAppiumTest {

    private AndroidDriver driver;

    @BeforeAll
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:app", "C:\\Users\\Dima\\IdeaProjects\\mqa\\mqa-homeworks\\2.2 UI Automator\\sample\\app\\build\\outputs\\apk\\debug\\app-debug.apk");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void inputSpace() {
        MobileElement textBefore = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.sendKeys(" ");
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el2.click();
        MobileElement textAfter = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        assertEquals(textBefore, textAfter);
    }

    @Test
    public void inputNothing() {
        MobileElement textBefore = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el2.click();
        MobileElement textAfter = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        assertEquals(textBefore, textAfter);
    }

    @Test
    public void newActivity() {
        MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.sendKeys("Hallo");
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        el2.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement textAfter = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/text");
        assertEquals("Hallo", textAfter.getText());
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}