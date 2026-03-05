package com.openclassrooms.mddapi.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequest {
    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Email(message = "Invalid email format")
    @Size(max = 255)
    private String email;
}
