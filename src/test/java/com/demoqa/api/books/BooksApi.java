package com.demoqa.api.books;

import com.demoqa.api.authorization.models.LoginResponseModel;
import com.demoqa.api.books.models.AddBookRequestModel;
import com.demoqa.api.books.models.DeleteBooksRequestModel;
import com.demoqa.api.books.models.IsbnModel;
import com.demoqa.api.books.models.NegativeBookResponseModel;
import io.restassured.response.Response;

import java.util.ArrayList;

import static com.demoqa.api.books.specs.BookSpec.*;
import static io.restassured.RestAssured.given;

public class BooksApi {

    public Response addBookToCollection(LoginResponseModel login, AddBookRequestModel bookData) {
        return given(bookRequestSpec)
                .header("Authorization", "Bearer " + login.getToken())
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(addBookResponseSpec)
                .extract().response();
    }

    public NegativeBookResponseModel addBookToCollectionWithoutLogin(AddBookRequestModel bookData) {
        return given(bookRequestSpec)
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(addBook401ResponseSpec)
                .extract().as(NegativeBookResponseModel.class);
    }

    public NegativeBookResponseModel addDuplicateBookToCollection(LoginResponseModel login, AddBookRequestModel bookData) {
        return given(bookRequestSpec)
                .header("Authorization", "Bearer " + login.getToken())
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(addBook400ResponseSpec)
                .extract().as(NegativeBookResponseModel.class);
    }

    public void deleteAllBooks(LoginResponseModel login) {
        given(bookRequestSpec)
                .header("Authorization", "Bearer " + login.getToken())
                .queryParam("UserId", login.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(deleteBook204ResponseSpec);
    }

    public void deleteCertainBook(LoginResponseModel login, DeleteBooksRequestModel deletedBookData) {
        given(bookRequestSpec)
                .header("Authorization", "Bearer " + login.getToken())
                .body(deletedBookData)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(deleteBook204ResponseSpec);
    }

    public AddBookRequestModel createDataForAddBookRequest(LoginResponseModel login, String isbn) {
        AddBookRequestModel bookData = new AddBookRequestModel();
        IsbnModel isbnData = new IsbnModel();
        bookData.setUserId(login.getUserId());
        isbnData.setIsbn(isbn);
        ArrayList<IsbnModel> isbnModelList = new ArrayList<>();
        isbnModelList.add(isbnData);
        bookData.setCollectionOfIsbns(isbnModelList);
        return bookData;
    }

    public DeleteBooksRequestModel createDataForDeleteBookRequest(LoginResponseModel login, String isbn) {
        DeleteBooksRequestModel deleteBookData = new DeleteBooksRequestModel();
        deleteBookData.setUserId(login.getUserId());
        deleteBookData.setIsbn(isbn);
        return deleteBookData;
    }
}
