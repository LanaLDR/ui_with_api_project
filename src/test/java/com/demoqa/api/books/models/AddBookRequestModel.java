package com.demoqa.api.books.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AddBookRequestModel {
    private String userId;
    private ArrayList<IsbnModel> collectionOfIsbns;
}
