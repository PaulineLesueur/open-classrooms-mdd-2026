package com.openclassrooms.mddapi.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity representing a registered user.
 * <p>
 * Each user has a unique UUID, a unique username and email, an encoded password,
 * and a list of topic subscriptions managed through the {@code subscriptions} join table.
 * </p>
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    /** Auto-generated UUID primary key stored as a VARCHAR(36). */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    /** Unique display name of the user (max 50 characters). */
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    /** Unique email address of the user. */
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    /** BCrypt-encoded password. */
    @Column(nullable = false)
    private String password;

    /** List of topics the user has subscribed to. */
    @ManyToMany
    @JoinTable(
        name = "subscriptions",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private List<Topic> subscriptions = new ArrayList<>();
}
