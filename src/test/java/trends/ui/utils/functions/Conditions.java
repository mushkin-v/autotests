package trends.ui.utils.functions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;
import org.openqa.selenium.WebElement;

public class Conditions {

    //Usage example: $("h1").shouldHave(css("font-size", "16px"));
    public static Condition css(final String propName, final String propValue) {
        return new Condition("css") {
            @Override
            public boolean apply(Driver driver, WebElement element) {
                return propValue.equalsIgnoreCase(element.getCssValue(propName));
            }
            @Override
            public String actualValue(Driver driver, WebElement element) {
                return element.getCssValue(propName);
            }
        };
    }
}
