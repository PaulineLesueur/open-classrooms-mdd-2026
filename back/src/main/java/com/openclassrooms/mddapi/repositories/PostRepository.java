package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Post} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations,
 * and declares an additional query method to fetch posts by topic.
 * </p>
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Retrieves all posts associated with a given topic.
     *
     * @param topicId the ID of the topic
     * @return a list of posts belonging to the specified topic
     */
    List<Post> findByTopicId(Integer topicId);
}
