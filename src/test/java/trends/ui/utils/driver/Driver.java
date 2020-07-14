package trends.ui.utils.driver;

import com.codeborne.selenide.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import trends.ui.utils.configs.AppConfig;
import trends.ui.utils.configs.BrowsersConfig;
import trends.ui.utils.configs.SelenoidConfig;
import trends.ui.utils.configs.TestConfig;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Driver {

    public static void initDriver() {
        // Get settings from command line
        TestConfig.initConfig();

        Configuration.startMaximized = true;
//        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = true;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Configuration.browserCapabilities = capabilities;

        if(TestConfig.isHeadless()) {
            Configuration.headless = true;
        } else {
            Configuration.headless = false;
        }

        switch (TestConfig.browser) {
            case BrowsersConfig.CHROME:
                Configuration.browser = BrowsersConfig.CHROME;
                if(TestConfig.isUsingSelenoid()) {
                    Configuration.remote = SelenoidConfig.testsEndpoint;
                    capabilities.setCapability("enableVNC", true);
                    capabilities.setCapability("enableVideo", false);
                }
                break;

            case BrowsersConfig.FIREFOX:
                Configuration.browser = BrowsersConfig.FIREFOX;
                if(TestConfig.isUsingSelenoid()) {
                    Configuration.remote = SelenoidConfig.testsEndpoint;
                    capabilities.setCapability("enableVNC", true);
                    capabilities.setCapability("enableVideo", false);
                }
                break;

            case BrowsersConfig.SAFARI:
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("browserName", "safari");
                capabilities.setCapability( "version", "13.1.1");
                capabilities.setCapability( "platform", "mac");
                capabilities.setCapability( "acceptInsecureCerts", false);
                capabilities.setCapability( "cleanSession", true);
                capabilities.setCapability( "maxInstances", "1");

                if(TestConfig.isUsingSelenoid()) {
                    Configuration.browser = BrowsersConfig.SAFARI;
                    Configuration.remote = SelenoidConfig.testsEndpoint;
                } else {
                    Configuration.browser = SafariProvider.class.getName();
                }
                break;

            case BrowsersConfig.SAFARI_ON_DEVICE:
                capabilities.setCapability("browserName", BrowsersConfig.SAFARI);
                capabilities.setCapability( "version", "13.1.1");
                capabilities.setCapability( "platform", "ios");
                capabilities.setCapability( "acceptInsecureCerts", false);
                capabilities.setCapability( "cleanSession", true);
                capabilities.setCapability( "javascriptEnabled", true);
                capabilities.setCapability( "maxInstances", "1");

                Configuration.timeout = 4000;
                Configuration.pollingInterval = 2000;
                Configuration.driverManagerEnabled = false;
                Configuration.reopenBrowserOnFail = true;
                Configuration.fastSetValue = true;
                Configuration.clickViaJs = true;

                if(TestConfig.isUsingSelenoid()) {
                    Configuration.browser = BrowsersConfig.SAFARI;
                    Configuration.remote = SelenoidConfig.testsEndpoint;
                } else {
                    Configuration.browser = SafariProvider.class.getName();
                }
                break;

            case BrowsersConfig.SAFARI_ON_DEVICE_SIMULATOR:
                capabilities.setCapability("browserName", BrowsersConfig.SAFARI);
                capabilities.setCapability( "version", "13.1.1");
                capabilities.setCapability( "platform", "ios");
                capabilities.setCapability( "acceptInsecureCerts", false);
                capabilities.setCapability( "cleanSession", true);
                capabilities.setCapability( "javascriptEnabled", true);
                capabilities.setCapability( "maxInstances", "1");
                capabilities.setCapability( "safari:useSimulator", true);

                Configuration.timeout = 4000;
                Configuration.pollingInterval = 2000;
                Configuration.driverManagerEnabled = false;
                Configuration.reopenBrowserOnFail = true;
                Configuration.fastSetValue = true;
                Configuration.clickViaJs = true;

                if(TestConfig.isUsingSelenoid()) {
                    Configuration.browser = BrowsersConfig.SAFARI;
                    Configuration.remote = SelenoidConfig.testsEndpoint;
                } else {
                    Configuration.browser = SafariProvider.class.getName();
                }
                break;

            default:
                Configuration.browser = BrowsersConfig.CHROME;
                break;
        }
    }

    private static class SafariProvider implements WebDriverProvider {
        @Override
        public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
            SafariOptions options = new SafariOptions(desiredCapabilities);
            return new SafariDriver(options);
        }
    }

    public static WebDriver currentDriver() {
        return WebDriverRunner.driver().getWebDriver();
    }

    public static void open(String url) {
        Selenide.open(url);
    }

    public static void refresh() {
        Selenide.refresh();
    }

    public static void executeJs(String script) {
        JavascriptExecutor js = (JavascriptExecutor)currentDriver();
        try {
            js.executeScript(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitForUrlContains(String urlChunk) {
        WebDriverWait wait = new WebDriverWait(currentDriver(), 10);
        wait.until(ExpectedConditions.urlContains(urlChunk));
    }

    public static void waitForUrlDoesNotContain(String urlChunk) {
        int maxTime = 20;
        while(  currentDriver().getCurrentUrl().contains(urlChunk)  && maxTime > 0) {
            wait(1);
            maxTime--;
        }
    }

    public static void maximize() {
        currentDriver().manage().window().maximize();
    }

    public static void changeWindowSize(int width, int height) {
        currentDriver().manage().window().setSize(new Dimension(width, height));
    }

    public static void clearCookies() {
        open(AppConfig.baseUrl);
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    public static void close() {
        currentDriver().quit();
    }

    public static void wait(int seconds)
    {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void takeScreenshot() {

        File scrFile = ((TakesScreenshot) currentDriver()).getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir")
                + File.separator + "test-output"
                + File.separator + "screenshots"
                + File.separator + " " + "screenshot_" +  (new SimpleDateFormat("HHmmssSSS").format(new Date())) + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<LogEntry> getBrowserLogs() {
        LogEntries log = currentDriver().manage().logs().get("browser");
        List<LogEntry> logList = log.getAll();
        return logList;
    }

    // COOKIES

    public static void addCookie(Cookie cookie) {
        currentDriver().manage().addCookie(cookie);
    }

    public static Cookie getCookie(String cookieName) {
        return currentDriver().manage().getCookieNamed(cookieName);
    }

    public static void deleteCookie(String cookieName) {
        currentDriver().manage().deleteCookieNamed(cookieName);
    }

}
