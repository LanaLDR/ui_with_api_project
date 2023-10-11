package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePageModel {
    private SelenideElement deleteAllBooksButton = $("#submit").shouldHave(text("Delete All Books")),
                            bookListTable = $(".ReactTable");

    @Step("Открываем страницу профиля")
    public ProfilePageModel openPage() {
        open("/profile");
        return this;
    }

    @Step("Кликаем по кнопке удаления всех книг")
    public ProfilePageModel deleteAllBooks() {
        deleteAllBooksButton.click();
        return this;
    }

    @Step("Проверяем, что книга отображается в коллекции")
    public ProfilePageModel checkBookIsVisible(String bookName) {
        bookListTable.shouldHave(text(bookName));
        return this;
    }
}
