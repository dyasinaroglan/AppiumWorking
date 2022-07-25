package example1;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;
import utils.App;
import utils.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test1 {

    AppiumDriverLocalService service;

    App app = App.APIDEMOS;
    Device device = Device.PIXEL12;


    @Test
    public void test1() throws InterruptedException {

        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .usingAnyFreePort().build();

        service.start();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:udid",device.udid);
        capabilities.setCapability("appium:version",device.version);
        capabilities.setCapability("appium:deviceName",device.deviceName);


        capabilities.setCapability("appium:appPackage",app.appPackage);
        capabilities.setCapability("appium:appActivity",app.appActivity);


        AppiumDriver<MobileElement> driver;
        driver = new AndroidDriver<>(service.getUrl(),capabilities);

        List<MobileElement> demoList = driver.findElements(By.id("android:id/text1"));
        for (int i = 0; i < demoList.size(); i++) {
            demoList.get(i).click();
            Thread.sleep(1000);
            driver.navigate().back();

        }

        driver.closeApp();
        service.stop();
    }

    @Test
    public void test2(){

        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .usingAnyFreePort().build();  //appium'u buradan Java ile çalıştırıyoruz.

        service.start();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:udid",device.udid);
        capabilities.setCapability("appium:version",device.version);
        capabilities.setCapability("appium:deviceName",device.deviceName);

        capabilities.setCapability("appium:appPackage",app.appPackage);
        capabilities.setCapability("appium:appActivity",app.appActivity);

        AppiumDriver<MobileElement> driver;
        driver = new AndroidDriver<>(service.getUrl(),capabilities);

        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Accessibility\"]")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Accessibility Node Querying\"]")).click();

        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.CheckBox")).click();
        driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.CheckBox")).click();

        driver.closeApp();
        service.stop();
    }

}
