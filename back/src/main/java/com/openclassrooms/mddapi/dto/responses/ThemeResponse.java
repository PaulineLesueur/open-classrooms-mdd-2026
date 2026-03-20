package com.openclassrooms.mddapi.dto.responses;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO representing a theme returned to the client.
 */
@Getter
@Setter
public class ThemeResponse {
    private Integer id;
    private String name;
    private String description;
}
