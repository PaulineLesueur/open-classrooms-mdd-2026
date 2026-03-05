package com.openclassrooms.mddapi.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false, name = "created_at")
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "author_id",
        nullable = false
    )
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "article_id",
        nullable = false
    )
    private Article article;
}
