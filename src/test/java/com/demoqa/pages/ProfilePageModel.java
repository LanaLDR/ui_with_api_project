package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePageModel {
    private SelenideElement deleteAllBooksButton = $("//*[@id='submit' and text()='Delete All Books']"),
            bookListTable = $(".ReactTable"),
            userNameValue = $("#userName-value");

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

    @Step("Проверяем, что книга не отображается в коллекции")
    public ProfilePageModel checkBookIsNotVisible(String bookName) {
        bookListTable.shouldNotHave(text(bookName));
        return this;
    }

    @Step("Проверяем имя пользователя в профиле")
    public ProfilePageModel checkUserName(String nameValue) {
        userNameValue.shouldHave(text(nameValue));
        return this;
    }
}
