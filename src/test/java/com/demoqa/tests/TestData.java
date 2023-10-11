package com.demoqa.tests;

import com.demoqa.api.authorization.models.LoginBodyModel;

public class TestData {

    private static final String LOGIN = "kekeker",
    PASSWORD = "Test123456@";

    private static final String NEGATIVE_LOGIN = "kekeker2";
    private static final String ISBN = "9781449325862";
    private static final String BOOK_NAME = "Git Pocket Guide";
    public static LoginBodyModel credentials = new LoginBodyModel(LOGIN, PASSWORD);
    public static LoginBodyModel negativeCredentials = new LoginBodyModel(NEGATIVE_LOGIN, PASSWORD);

    public static String getISBN() {
        return ISBN;
    }
    public static String getBookName() {
        return BOOK_NAME;
    }
}
