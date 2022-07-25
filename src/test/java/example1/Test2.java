package example1;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import utils.App;
import utils.Device;

import javax.management.MalformedObjectNameException;

public class Test2 {

    AppiumDriverLocalService service ; //Appium açık olmadığında java ile buradan açabiliyoruz.

    App app = App.APIDEMOS;
    Device device = Device.PIXEL12;


    @Test
    public void test1() throws InterruptedException {

        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4753)
                .usingAnyFreePort().build();
        service.start();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:udid",device.udid);
        capabilities.setCapability("appium:version",device.version);
        capabilities.setCapability("appium:deviceName",device.deviceName);
        capabilities.setCapability("appium:platformName",device.platformName);

        capabilities.setCapability("appium:appPackage",app.appPackage);
        capabilities.setCapability("appium:appActivity",app.appActivity);

        AppiumDriver<MobileElement> driver;

        driver = new AndroidDriver<>(service.getUrl(),capabilities);

        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Animation\"]")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Seeking\"]")).click();
        driver.findElement(By.id("io.appium.android.apis:id/startButton")).click();
        Thread.sleep(2000);

        Actions actions = new Actions(driver);
        MobileElement element = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.SeekBar"));

        actions.dragAndDropBy(element,100,0).build().perform();

        driver.closeApp();
        service.stop();
    }


}
