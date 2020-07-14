package trends.ui.utils.pagesSetup;

import trends.ui.pages.HomePage;

public class PagesSetup {

    public HomePage homePage;

    public PagesSetup() {
        homePage = PageBuilder.buildHomePage();
    }
}
