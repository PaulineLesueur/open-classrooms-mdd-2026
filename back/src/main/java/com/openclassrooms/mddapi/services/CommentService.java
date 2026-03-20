package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.requests.CommentRequest;
import com.openclassrooms.mddapi.dto.responses.CommentResponse;
import com.openclassrooms.mddapi.entities.Post;
import com.openclassrooms.mddapi.entities.Comment;
import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.mappers.CommentMapper;
import com.openclassrooms.mddapi.repositories.PostRepository;
import com.openclassrooms.mddapi.repositories.CommentRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class handling business logic for comments.
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    /**
     * Retrieves all comments for a given post.
     *
     * @param postId the ID of the post
     * @return a list of comments as response DTOs
     */
    public List<CommentResponse> getByPostId(Long postId) {
        return commentRepository.findByPostId(postId)
                .stream()
                .map(commentMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Creates and persists a new comment.
     *
     * @param request the DTO containing the comment data
     * @return the created comment as a response DTO
     * @throws RuntimeException if the author or post is not found
     */
    public CommentResponse create(CommentRequest request) {
        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("User not found with id : " + request.getAuthorId()));
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found with id : " + request.getPostId()));
        Comment comment = commentMapper.toEntity(request, author, post);
        return commentMapper.toResponse(commentRepository.save(comment));
    }
}
