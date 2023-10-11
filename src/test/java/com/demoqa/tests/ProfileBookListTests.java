package com.demoqa.tests;

import com.demoqa.api.authorization.models.LoginResponseModel;
import com.demoqa.api.books.models.AddBookRequestModel;
import com.demoqa.api.books.models.DeleteBooksRequestModel;
import com.demoqa.api.books.models.NegativeBookResponseModel;
import com.demoqa.helpers.WithLogin;
import com.demoqa.pages.ProfilePageModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.demoqa.tests.TestData.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ProfileBookListTests extends TestBase {

    ProfilePageModel profilePage = new ProfilePageModel();

    @Test
    @DisplayName("Добавление книги без авторизации")
    void negative401addBookToCollectionTest() {
        LoginResponseModel loginResponse = loginApi.login(negativeCredentials);
        AddBookRequestModel bookData = booksApi.createDataForAddBookRequest(loginResponse, getISBN());

        NegativeBookResponseModel response = booksApi.addBookToCollectionWithoutLogin(bookData);
        profilePage.openPage();
        step("Проверка полей ответа", () -> {
            assertThat(response.getCode()).isEqualTo("1200");
            assertThat(response.getMessage()).isEqualTo("User not authorized!");
        });
    }

    @Test
    @WithLogin
    @DisplayName("Добавление дубля книги в коллецию")
    void negative400addBookToCollectionTest() {
        LoginResponseModel loginResponse = loginApi.login(negativeCredentials);
        AddBookRequestModel bookData = booksApi.createDataForAddBookRequest(loginResponse, getISBN());

        NegativeBookResponseModel response = booksApi.addDuplicateBookToCollection(loginResponse, bookData);

        step("Проверка полей ответа", () -> {
            assertThat(response.getCode()).isEqualTo("1210");
            assertThat(response.getMessage()).isEqualTo("ISBN already present in the User's Collection!");
        });
    }

    @Test
    @WithLogin
    @DisplayName("Успешное удаленние опредленной книги")
    void successfulDeleteBook() {
        LoginResponseModel loginResponse = loginApi.login(credentials);
        DeleteBooksRequestModel deletedBooksData = booksApi.createDataForDeleteBookRequest(loginResponse, getISBN());
        AddBookRequestModel bookData = booksApi.createDataForAddBookRequest(loginResponse, getISBN());

        booksApi.deleteCertainBook(loginResponse, deletedBooksData);

        profilePage.openPage()
                .checkBookIsNotVisible(getBookName());
    }

    @Test
    @WithLogin
    @DisplayName("Успешное добавление книги в коллекцию")
    void addBookToCollectionTest() {
        LoginResponseModel loginResponse = loginApi.login(credentials);
        AddBookRequestModel bookData = booksApi.createDataForAddBookRequest(loginResponse, getISBN());
        booksApi.deleteAllBooks(loginResponse);

        booksApi.addBookToCollection(loginResponse, bookData);

        profilePage.openPage()
                .checkBookIsVisible(getBookName());
    }

    @Test
    @WithLogin
    @DisplayName("Успешное добавление книги в коллекцию после удаления 1 книги")
    void addBookToCollectionAfterDelete1BookTest() {
        LoginResponseModel loginResponse = loginApi.login(credentials);
        DeleteBooksRequestModel deletedBooksData = booksApi.createDataForDeleteBookRequest(loginResponse, getISBN());
        AddBookRequestModel bookData = booksApi.createDataForAddBookRequest(loginResponse, getISBN());
        booksApi.deleteAllBooks(loginResponse);
        booksApi.addBookToCollection(loginResponse, bookData);

        booksApi.deleteCertainBook(loginResponse, deletedBooksData);
        booksApi.addBookToCollection(loginResponse, bookData);

        profilePage.openPage()
                .checkBookIsVisible(getBookName());
    }
}
