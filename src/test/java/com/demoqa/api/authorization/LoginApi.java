package com.demoqa.api.authorization;

import com.demoqa.api.authorization.models.LoginBodyModel;
import com.demoqa.api.authorization.models.LoginResponseModel;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class LoginApi {

    public static LoginResponseModel login(LoginBodyModel authData) {
         return given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(ContentType.JSON)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
    }
}
