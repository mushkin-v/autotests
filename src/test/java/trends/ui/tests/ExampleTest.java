package trends.ui.tests;

import trends.ui.utils.baseTest.BaseTest;
import trends.ui.utils.driver.Driver;
import org.testng.annotations.Test;


public class ExampleTest extends BaseTest
{
    @Test
    public void exampleTest() {
        pagesSetup.homePage.open();
        pagesSetup.homePage.search("Selenide");
        Driver.wait(1);

        logger.info("Sample info message");
        logger.warn("Sample warn message");
        logger.error("Sample error message");
        logger.fatal("Sample fatal message");

        softAssert.assertEquals(2,2);
        softAssert.assertAll();
    }
}
