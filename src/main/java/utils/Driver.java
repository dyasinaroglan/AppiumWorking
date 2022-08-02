package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Driver {

    private static AppiumDriver<MobileElement> driver;
    private static AppiumDriverLocalService service;


    public static void runAppium(){
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .usingAnyFreePort().build();

        service.start();
    }
    public static void stopAppium(){
        service.stop();
    }
    private static DesiredCapabilities setcapa(Device device, App app){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:udid",device.udid);
        capabilities.setCapability("appium:version",device.version);
        capabilities.setCapability("appium:deviceName",device.deviceName);
        capabilities.setCapability("appium:platformName",device.platformName);

        capabilities.setCapability("appium:appPackage",app.appPackage);
        capabilities.setCapability("appium:appActivity",app.appActivity);

        return capabilities;
    }


    public static AppiumDriver<MobileElement> getDriver(Device device,App app){   //bir tane App ve bir tane cihaz gönderiyoruz
        runAppium();
        AppiumDriver<MobileElement> driver;
        driver = new AndroidDriver<>(service.getUrl(),setcapa(device,app));
                                                     //capabilities artık setcapa olduğu için setcapa methodu çağırılıyor

        return driver;

    }
}
