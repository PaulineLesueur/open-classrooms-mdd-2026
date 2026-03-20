package com.openclassrooms.mddapi.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Response DTO representing a comment returned to the client.
 */
@Getter
@Setter
public class CommentResponse {
    private Long id;
    private String body;
    private LocalDate createdAt;
    private String authorUsername;
}