package trends.ui.pages;

import com.codeborne.selenide.SelenideElement;
import trends.ui.utils.pagesSetup.BasePage;
import trends.ui.utils.configs.TestConfig;
import trends.ui.utils.driver.Driver;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BasePage {

    SelenideElement search = $(byXpath("//input[@aria-label=\"Пошук\"]"));

    public HomePage(String pageUrl) {
        super(pageUrl);
    }

    public void search(String word) {
        if(TestConfig.isMobile()) Driver.wait(1);
        search.val(word).submit();
    }
}
