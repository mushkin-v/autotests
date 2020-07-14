package trends.ui.utils.pagesSetup;
import com.codeborne.selenide.Selenide;
import trends.ui.utils.configs.AppConfig;
import trends.ui.utils.functions.Trim;

public abstract class BasePage {

    protected String pageUrl;

    public BasePage(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public void open() {
        String url = Trim.rtrim(AppConfig.baseUrl, "/") + "/" + Trim.ltrim(pageUrl, "/");
        Selenide.open(url);
    }
}
