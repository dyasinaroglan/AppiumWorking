package utils;

import io.appium.java_client.AppiumDriver;

public class Utils {

    public static AppiumDriver openApp(Device device, App app){

        Driver.runAppium();
        return Driver.getDriver(device,app);

    }
}
