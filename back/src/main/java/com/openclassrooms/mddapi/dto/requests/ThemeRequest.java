package com.openclassrooms.mddapi.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ThemeRequest {
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Theme name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;
}
