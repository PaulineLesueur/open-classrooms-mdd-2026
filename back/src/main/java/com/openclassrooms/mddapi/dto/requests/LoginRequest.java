package com.openclassrooms.mddapi.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "Identifier is mandatory")
    private String identifier;

    @NotBlank(message = "Password is mandatory")
    private String password;
}