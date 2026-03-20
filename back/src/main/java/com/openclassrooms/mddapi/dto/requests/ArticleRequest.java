package com.openclassrooms.mddapi.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request DTO for creating a new article.
 */
@Getter
@Setter
public class ArticleRequest {
    @NotBlank(message = "Title is mandatory")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "Content is mandatory")
    private String content;

    @NotNull(message = "Theme is mandatory")
    private Integer themeId;

    @NotBlank(message = "Author is mandatory")
    private String authorId;
}
