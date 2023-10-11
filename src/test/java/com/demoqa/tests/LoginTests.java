package com.demoqa.tests;

import com.demoqa.api.authorization.models.LoginResponseModel;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.demoqa.tests.TestData.*;

public class LoginTests extends TestBase {

    @Test
    void successfulLoginWithApiTest() {
        LoginResponseModel loginResponse = loginApi.login(credentials);

        open("/favicon.ico");

        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));

        open("/profile");
        $("#userName-value").shouldHave(text(loginResponse.getUsername()));
    }
}
