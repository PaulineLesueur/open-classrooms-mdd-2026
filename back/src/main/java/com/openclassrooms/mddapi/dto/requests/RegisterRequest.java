package com.openclassrooms.mddapi.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Request DTO for user registration.
 * The password must be at least 8 characters long and contain at least one uppercase letter,
 * one lowercase letter, one digit, and one special character.
 */
@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Username is mandatory")
    @Size(max = 50)
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    @Size(max = 255)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*]).{8,}$",
            message = "Password must contain at least 8 characters, one uppercase, one lowercase, one digit and one special character"
    )
    private String password;
}