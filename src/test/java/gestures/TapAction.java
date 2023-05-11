package gestures;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.WatchEvent;
import java.time.Duration;

public class TapAction {

    @Test
    public void tapTest() throws MalformedURLException {

        File apkFile = new File("src/test/resources/ApiDemos-debug.apk");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setPlatform(Platform.ANDROID);//instead of string in setCapability, it takes platform as parameter
        caps.setCapability("deviceName","checkPhone");
        caps.setCapability("app",apkFile.getAbsolutePath());

        URL serverUrl = new URL("http://0.0.0.0:4723/wd/hub");

        AndroidDriver driver = new AndroidDriver(serverUrl,caps);
        WebElement viewButton =
                driver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));

            //this is tap action
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(viewButton))).perform();
        //now we doing the longprocess action

        WebElement expandableLists = driver.findElementByAccessibilityId("Expandable Lists");
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(expandableLists))).perform();

        WebElement customerAdapter = driver.findElementByAccessibilityId("1. Custom Adapter");
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(customerAdapter))).perform();



        WebElement peopleNames = driver.findElement(By.xpath("//*[@text='People Names']"));
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(peopleNames))
                .withDuration(Duration.ofSeconds(2)))// //want to specified how long to do long press
                .perform();
    }


    @Test
    public void scrollTest() throws MalformedURLException {
        File apkFile = new File("src/test/resources/ApiDemos-debug.apk");//command shift v to see all history that we have copied
    DesiredCapabilities caps = new DesiredCapabilities();
//    caps.setCapability("deviceName","checkPhone");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "checkPhone");
    caps.setCapability(MobileCapabilityType.APP, apkFile.getAbsolutePath());
    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
    caps.setPlatform(Platform.ANDROID);

    URL appiumServerUrl = new URL("http://0.0.0.0:4723/wd/hub");

    AndroidDriver driver =new AndroidDriver<>(appiumServerUrl,caps);
        WebElement viewButton =
                driver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(viewButton))).perform();


//        WebElement tabsButton = driver.findElementByAccessibilityId("Tabs");
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Tabs\"))");//method using to scroll,take string as parameter
        //create ob new uiScrollable and pass//pass string into string so use back slash \"value\"


    }








}
