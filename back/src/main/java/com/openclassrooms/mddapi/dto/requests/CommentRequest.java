package com.openclassrooms.mddapi.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Request DTO for creating a new comment on a post.
 */
@Getter
@Setter
public class CommentRequest {
    @NotBlank(message = "Content is mandatory")
    private String body;

    @NotBlank(message = "Author is mandatory")
    private String authorId;

    @NotNull(message = "Post is mandatory")
    private Long postId;
}
