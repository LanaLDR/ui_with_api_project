package com.demoqa.tests;

import com.demoqa.api.authorization.models.LoginResponseModel;
import com.demoqa.helpers.WithLogin;
import com.demoqa.pages.ProfilePageModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.demoqa.tests.TestData.*;

public class LoginTests extends TestBase {

    ProfilePageModel profilePage = new ProfilePageModel();

    @Test
    @WithLogin
    @DisplayName("Авторизация с корректными данными")
    void successfulLoginWithApiTest() {
        LoginResponseModel loginResponse = loginApi.login(credentials);

        profilePage.openPage()
                        .checkUserName(loginResponse.getUsername());
    }
}
