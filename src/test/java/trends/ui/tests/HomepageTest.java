package trends.ui.tests;

import trends.ui.utils.baseTest.BaseTest;
import org.testng.annotations.Test;
import trends.ui.utils.configs.AppConfig;
import trends.ui.utils.driver.Driver;


public class HomepageTest extends BaseTest
{
    @Test
    public void openTest() {
        pagesSetup.homePage.open();
        softAssert.assertEquals( Driver.currentDriver().getCurrentUrl(), AppConfig.baseUrl);
        softAssert.assertAll();
    }
}
