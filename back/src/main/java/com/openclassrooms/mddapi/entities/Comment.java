package com.openclassrooms.mddapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * JPA entity representing a comment on a post.
 * <p>
 * A comment is written by a {@link User} and attached to a {@link Post}.
 * The creation date is automatically set before the first database insert.
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
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    /** Post this comment belongs to. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    /**
     * Sets {@code createdAt} to the current date before the entity is first persisted.
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }
}
