package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Comment} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations,
 * and declares an additional query method to fetch comments by article.
 * </p>
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Retrieves all comments associated with a given article.
     *
     * @param articleId the ID of the article
     * @return a list of comments belonging to the specified article
     */
    List<Comment> findByArticleId(Long articleId);
}
