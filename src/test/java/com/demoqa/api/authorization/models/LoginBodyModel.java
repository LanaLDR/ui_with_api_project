package com.demoqa.api.authorization.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginBodyModel {

    private String userName, password;
}
