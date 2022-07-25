package example1;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import utils.App;
import utils.Device;

import java.util.List;

public class Test3 {

    AppiumDriverLocalService service;

    App app = App.APIDEMOS;
    Device device = Device.PIXEL12;

    @Test
    public void test() throws InterruptedException {

        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1").usingPort(4753).usingAnyFreePort().build();

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

        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"App\"]")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Menu\"]")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Inflate from XML\"]")).click();

        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.Spinner")).click();
        List<MobileElement> list = driver.findElements(By.id("android:id/text1"));

        list.get(randomSayı(list.size())).click();

        driver.closeApp();
        service.stop();
    }

    public static int randomSayı(int size){
        return (int) (Math.random() * size);

    }
}
