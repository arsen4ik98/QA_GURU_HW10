import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@Feature("Issue в репозитории")
@Story("Создание Issue")
@Owner("arsen4ik98")
@Link(value = "Testing", url = "https://github.com/arsen4ik98/QA.GURU_HW9")
public class StepTest {

    private static final String REPOSITORY = "arsen4ik98/QA.GURU_HW9";
    private static final String ISSUE = "#1";

    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Отображение Issue c помощью лямбда")
    @Test
            public void testLambdaStep(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Открываем репозиторий" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем вкладку Issues", () -> {
        $("#issues-tab").click();
        });
        step("Проверить, что issue " + ISSUE + " существует", () -> {
            $(withText(ISSUE)).should(Condition.exist);
        });

    }

    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Отображение Issue c помощью аннотацией")
    @Test
    public void testAnnotatedStep() {

        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }


}

