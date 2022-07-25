package utils;

public enum App {

    APIDEMOS("io.appium.android.apis","io.appium.android.apis.ApiDemos");
              //Package                         Activities

    public String appPackage;
    public String appActivity;


    App(String appPackage, String appActivity) {
        this.appPackage = appPackage;
        this.appActivity = appActivity;
    }
}
