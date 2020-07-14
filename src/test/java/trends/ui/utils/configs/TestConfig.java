package trends.ui.utils.configs;

public class TestConfig {

    public static String browser = BrowsersConfig.CHROME;
    public static String headless = "0";
    public static String selenoid = "0";

    public static void initConfig() {
        browser = System.getProperty("browser") == null ? BrowsersConfig.CHROME : System.getProperty("browser");
        headless = System.getProperty("headless") == null ? "0" : System.getProperty("headless");
        selenoid = System.getProperty("selenoid") == null ? "0" : System.getProperty("selenoid");
    }

    public static boolean isMobile() {
        if(browser.equals(BrowsersConfig.SAFARI_ON_DEVICE)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isHeadless() {
        return headless.contains("1");
    }

    public static boolean isUsingSelenoid() {
        return selenoid.contains("1");
    }
}
