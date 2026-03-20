package com.openclassrooms.mddapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * JPA entity representing a comment on an article.
 * <p>
 * A comment is written by a {@link User} and attached to an {@link Article}.
 * The creation date is automatically set before the first database insert.
 * </p>
 */
@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    /** Auto-incremented primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Text content of the comment. */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    /** Date the comment was created, set automatically on first persist. */
    @Column(nullable = false, name = "created_at")
    private LocalDate createdAt;

    /** User who posted the comment. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "author_id",
        nullable = false
    )
    private User author;

    /** Article this comment belongs to. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "article_id",
        nullable = false
    )
    private Article article;

    /**
     * Sets {@code createdAt} to the current date before the entity is first persisted.
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }
}
