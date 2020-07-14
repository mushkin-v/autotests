package trends.ui.utils.pagesSetup;

import trends.ui.pages.HomePage;

public class PageBuilder {

    public static HomePage buildHomePage() {
        return new HomePage("/");
    }
}
