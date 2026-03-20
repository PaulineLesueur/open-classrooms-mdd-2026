package com.openclassrooms.mddapi.Seeders;

import com.openclassrooms.mddapi.entities.Post;
import com.openclassrooms.mddapi.entities.Comment;
import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.repositories.PostRepository;
import com.openclassrooms.mddapi.repositories.CommentRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(4)
@RequiredArgsConstructor
public class CommentSeeder implements CommandLineRunner {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void run(String... args) {
        if (commentRepository.count() == 0) {
            User author = userRepository.findAll()
                    .stream()
                    .findFirst()
                    .orElse(null);
            Post post = postRepository.findAll()
                    .stream()
                    .findFirst()
                    .orElse(null);

            if (author == null || post == null) {
                System.out.println("No User or Post found in database. Comment seed ignored.");
                return;
            }

            List<Comment> comments = List.of(
                    createComment("Super article, merci !", author, post),
                    createComment("Très instructif !", author, post),
                    createComment("J'ai appris beaucoup de choses.", author, post)
            );

            commentRepository.saveAll(comments);
            System.out.println("Comments successfully initialized");
        }
    }

    private Comment createComment(String body, User author, Post post) {
        Comment comment = new Comment();
        comment.setBody(body);
        comment.setAuthor(author);
        comment.setPost(post);
        return comment;
    }
}
