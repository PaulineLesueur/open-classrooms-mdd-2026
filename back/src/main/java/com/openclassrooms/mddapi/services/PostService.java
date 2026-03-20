package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.requests.PostRequest;
import com.openclassrooms.mddapi.dto.responses.PostResponse;
import com.openclassrooms.mddapi.entities.Post;
import com.openclassrooms.mddapi.entities.Topic;
import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.repositories.PostRepository;
import com.openclassrooms.mddapi.repositories.TopicRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class handling business logic for posts.
 */
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final PostMapper postMapper;

    /**
     * Retrieves all posts.
     *
     * @return a list of all posts as response DTOs
     */
    public List<PostResponse> getAll() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single post by its ID.
     *
     * @param id the ID of the post
     * @return the post as a response DTO
     * @throws RuntimeException if no post is found with the given ID
     */
    public PostResponse getById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id : " + id));
        return postMapper.toResponse(post);
    }

    /**
     * Creates and persists a new post.
     *
     * @param request the DTO containing the post data
     * @return the created post as a response DTO
     * @throws RuntimeException if the author or topic is not found
     */
    public PostResponse create(PostRequest request) {
        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("User not found with id : " + request.getAuthorId()));
        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found with id : " + request.getTopicId()));
        Post post = postMapper.toEntity(request, author, topic);
        return postMapper.toResponse(postRepository.save(post));
    }
}
