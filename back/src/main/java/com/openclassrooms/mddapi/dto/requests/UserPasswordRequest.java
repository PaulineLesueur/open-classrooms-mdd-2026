package com.openclassrooms.mddapi.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Request DTO for updating a user's password.
 */
@Getter
@Setter
public class UserPasswordRequest {

    @NotBlank(message = "Password is mandatory")
    private String password;
}