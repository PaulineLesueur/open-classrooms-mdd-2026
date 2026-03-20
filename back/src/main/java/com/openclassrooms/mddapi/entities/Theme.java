package com.openclassrooms.mddapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * JPA entity representing a topic theme that users can subscribe to.
 * <p>
 * Articles are categorised by theme, and users can follow themes to receive
 * a personalised article feed.
 * </p>
 */
@Getter
@Setter
@Entity
@Table(name = "themes")
public class Theme {
    /** Auto-incremented primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Unique name of the theme (max 100 characters). */
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    /** Description explaining the topic covered by this theme. */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    /** List of users who have subscribed to this theme. */
    @ManyToMany(mappedBy = "subscriptions")
    private List<User> subscribers;
}
