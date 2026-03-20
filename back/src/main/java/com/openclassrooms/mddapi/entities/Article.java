package com.openclassrooms.mddapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * JPA entity representing a blog article.
 * <p>
 * An article belongs to a single author ({@link User}) and a single {@link Theme}.
 * The creation date is automatically set before the first database insert.
 * </p>
 */
@Getter
@Setter
@Entity
@Table(name = "articles")
public class Article {
    /** Auto-incremented primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Title of the article (max 255 characters). */
    @Column(nullable = false, length = 255)
    private String title;

    /** Full text content of the article. */
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    /** Date the article was created, set automatically on first persist. */
    @Column(nullable = false, name = "created_at")
    private LocalDate createdAt;

    /** User who wrote the article. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    /** Theme the article is associated with. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;

    /** List of comments posted on this article. */
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    /**
     * Sets {@code createdAt} to the current date before the entity is first persisted.
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }
}
