package com.openclassrooms.mddapi.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentRequest {
    @NotBlank(message = "Content is mandatory")
    private String body;

    @NotBlank(message = "Author is mandatory")
    private String authorId;

    @NotBlank(message = "Article is mandatory")
    private Long articleId;
}
