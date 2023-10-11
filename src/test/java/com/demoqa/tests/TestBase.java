package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.api.authorization.LoginApi;
import com.demoqa.api.books.BooksApi;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    LoginApi loginApi = new LoginApi();
    BooksApi booksApi = new BooksApi();

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void shutDown() {
        closeWebDriver();
    }
}
