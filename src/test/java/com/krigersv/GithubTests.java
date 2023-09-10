package com.krigersv;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class GithubTests extends TestBase {
    SelenideElement page = $(".search-input-container"),
                    input = $("#query-builder-test");

     String REPOSITORY = "StasK86/hausaufgabe_10",
            NAME = "Welcome to issues!";

    @Test
    @DisplayName("Issue (Welcome to issues) существует в репозитории")
    public void issueExistTest() {
        open(baseUrl);
        page.click();
        input.sendKeys(REPOSITORY);
        input.submit();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(NAME)).should(Condition.exist);
    }

    @Test
    @DisplayName("step с использованием лямбда")
    public void issueLambdaTest() {
        step("Главная страница сайта", () -> open(baseUrl));
        step("Поиск репозитория" + REPOSITORY, () -> {
            page.click();
            input.sendKeys(REPOSITORY);
            input.submit();
        });
        step("Кликаем по ссылке " + REPOSITORY + " в результатах поиска", () ->
                $(linkText(REPOSITORY)).click());
        step("Открываем", () -> $("#issues-tab").click());
        step("Проверяем что" + NAME + "существует", () ->
                $(withText(NAME)).should(Condition.exist));
    }

    @Test
    @DisplayName("Тест с использованием аннотации @Step")
    public void issueAnnotatedTest() {
        WebSteps steps = new WebSteps();
                steps.openPage()
                .searchField(REPOSITORY)
                .clickName(REPOSITORY)
                .openTab()
                .checkName(NAME);
    }
}

