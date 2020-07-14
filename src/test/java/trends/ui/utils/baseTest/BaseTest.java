package trends.ui.utils.baseTest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;
import trends.ui.utils.pagesSetup.PagesSetup;
import trends.ui.utils.driver.Driver;

public class BaseTest {

    protected PagesSetup pagesSetup;
    protected SoftAssert softAssert;
    protected Logger logger;

    @BeforeClass
    public void setUp() {

        Driver.initDriver();

        pagesSetup = new PagesSetup();
        softAssert = new SoftAssert();

        logger = LogManager.getLogger("");
        DOMConfigurator.configure("src/test/resources/log4j.xml");
    }

    @AfterMethod
    public void clearCookies() {
        Driver.clearCookies();
    }

    @AfterClass
    public void tearDown() {
        Driver.close();
    }
}
