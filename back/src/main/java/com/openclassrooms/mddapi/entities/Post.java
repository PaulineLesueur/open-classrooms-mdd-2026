package com.openclassrooms.mddapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * JPA entity representing a blog post.
 * <p>
 * A post belongs to a single author ({@link User}) and a single {@link Topic}.
 * The creation date is automatically set before the first database insert.
 */
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    /** Auto-incremented primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Title of the post (max 255 characters). */
    @Column(nullable = false, length = 255)
    private String title;

    /** Full text content of the post. */
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    /** Date the post was created, set automatically on first persist. */
    @Column(nullable = false, name = "created_at")
    private LocalDate createdAt;

    /** User who wrote the post. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    /** Topic the post is associated with. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    /** List of comments posted on this post. */
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    /**
     * Sets {@code createdAt} to the current date before the entity is first persisted.
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }
}
