package com.openclassrooms.mddapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * JPA entity representing a topic that users can subscribe to.
 * <p>
 * Posts are categorised by topic, and users can follow topics to receive
 * a personalised post feed.
 */
@Getter
@Setter
@Entity
@Table(name = "topics")
public class Topic {
    /** Auto-incremented primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Unique name of the topic (max 100 characters). */
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    /** Description explaining the subject covered by this topic. */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    /** List of users who have subscribed to this topic. */
    @ManyToMany(mappedBy = "subscriptions")
    private List<User> subscribers;
}
