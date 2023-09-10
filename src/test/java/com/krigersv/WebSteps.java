package com.krigersv;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    private SelenideElement page = $(".search-input-container"),
            search = $("#query-builder-test");

    @Step("Открываем главную страницу")
    public WebSteps openPage() {
        open("https://github.com");
        return this;
    }

    @Step("Выполняем поиск по имени репозитория {repo}")
    public WebSteps searchField(String repo) {
        page.click();
        search.sendKeys(repo);
        search.submit();
        return this;
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public WebSteps clickName(String repo) {
        $(linkText(repo)).click();
        return this;
    }

    @Step("Открываем таб Issues")
    public WebSteps openTab() {
        $("#issues-tab").click();
        return this;
    }

    @Step("Проверяем наличие Issue с именем '{issue}")
    public void checkName(String issue) {
        $(withText(issue)).should(Condition.exist);
    }
}

