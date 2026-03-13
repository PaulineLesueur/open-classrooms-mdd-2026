package com.openclassrooms.mddapi.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private String authorUsername;
    private String themeName;
}
