import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

@Feature("Issue в репозитории")
@Story("Создание Issue")
@Owner("arsen4ik98")
public class SelenideTest {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }
        @BeforeEach
        void addListeners() {
            SelenideLogger.addListener("allure", new AllureSelenide());
        }

    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Отображение Issue c обычным способом")
    @Test
    public void testIssueSearch() {

        SelenideLogger.addListener("allure", new AllureSelenide());
        open("");
        $(".search-input").click();
        $("#query-builder-test").sendKeys("arsen4ik98/QA.GURU_HW9");
        $("#query-builder-test").submit();
        $(linkText("arsen4ik98/QA.GURU_HW9")).click();
        $("#issues-tab").click();
        $(withText("#1")).should(Condition.exist);
    }

}
