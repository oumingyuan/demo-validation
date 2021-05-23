package com.example.demovalidation.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserLogin {

    @NotBlank
    @Size(max = 10)
    private String username;

    private String password;
}
