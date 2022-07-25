package utils;

public enum Device {

    PIXEL12("emulator-5554","11","Pixel2","Android");


    public String udid;
    public String version;
    public String deviceName;
    public String platformName;


    Device(String udid, String version, String deviceName, String platformName) {
        this.udid = udid;
        this.version = version;
        this.deviceName = deviceName;
        this.platformName = platformName;
    }
}
