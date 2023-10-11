package com.demoqa.tests;

import com.demoqa.api.authorization.LoginApi;
import com.demoqa.api.books.BooksApi;
import com.demoqa.config.GlobalConfig;
import com.demoqa.config.GlobalConfigProvider;
import com.demoqa.helpers.AttachmentAllure;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    GlobalConfig config = ConfigFactory.create(GlobalConfig.class, System.getProperties());
    static final GlobalConfigProvider provider = new GlobalConfigProvider();
    LoginApi loginApi = new LoginApi();
    BooksApi booksApi = new BooksApi();

    @BeforeAll
    static void beforeAll() {
        provider.setUp();
    }

    @AfterEach
    void shutDown() {
        AttachmentAllure.screenshotAs("Last screenshot");
        AttachmentAllure.pageSource();
        AttachmentAllure.browserConsoleLogs();
        if (config.isRemote()) {
            AttachmentAllure.addVideo();
        }
        closeWebDriver();
        closeWebDriver()
        ;
    }
}
