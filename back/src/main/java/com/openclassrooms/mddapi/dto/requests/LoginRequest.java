package com.openclassrooms.mddapi.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Request DTO for user login.
 * The {@code identifier} field accepts either an email address or a username.
 */
@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "Identifier is mandatory")
    private String identifier;

    @NotBlank(message = "Password is mandatory")
    private String password;
}