package com.demoqa.helpers.extentions;

import com.demoqa.api.authorization.models.LoginResponseModel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.demoqa.api.authorization.LoginApi.login;
import static com.demoqa.tests.TestData.credentials;

public class LoginExtension implements BeforeEachCallback {

    @Step("Добавляем куки для авторизации")
    @Override
    public void beforeEach(ExtensionContext context) {
        LoginResponseModel login = login(credentials);

        open("/favicon.ico");

        getWebDriver().manage().addCookie(new Cookie("expires", login.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("userID", login.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", login.getToken()));
    }
}
