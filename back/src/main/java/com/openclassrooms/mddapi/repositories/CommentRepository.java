package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Comment} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations,
 * and declares an additional query method to fetch comments by post.
 * </p>
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Retrieves all comments associated with a given post.
     *
     * @param postId the ID of the post
     * @return a list of comments belonging to the specified post
     */
    List<Comment> findByPostId(Long postId);
}
