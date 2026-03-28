package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.requests.PostRequest;
import com.openclassrooms.mddapi.dto.responses.PostResponse;
import com.openclassrooms.mddapi.entities.Post;
import com.openclassrooms.mddapi.entities.Topic;
import com.openclassrooms.mddapi.entities.User;
import org.springframework.stereotype.Component;

/**
 * Mapper component responsible for converting between {@link Post} entities
 * and their corresponding DTOs.
 */
@Component
public class PostMapper {

    /**
     * Converts a {@link Post} entity to a {@link PostResponse} DTO.
     *
     * @param post the post entity to convert
     * @return the corresponding response DTO
     */
    public PostResponse toResponse(Post post) {
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setCreatedAt(post.getCreatedAt());
        response.setAuthorUsername(post.getAuthor().getUsername());
        response.setTopicName(post.getTopic().getName());
        return response;
    }

    /**
     * Converts a {@link PostRequest} DTO to a {@link Post} entity.
     *
     * @param request the request DTO containing post data
     * @param author  the user who authored the post
     * @param topic   the topic associated with the post
     * @return a new {@link Post} entity (not yet persisted)
     */
    public Post toEntity(PostRequest request, User author, Topic topic) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(author);
        post.setTopic(topic);
        return post;
    }
}
