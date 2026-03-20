package com.openclassrooms.mddapi.Seeders;

import com.openclassrooms.mddapi.entities.Post;
import com.openclassrooms.mddapi.entities.Topic;
import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.repositories.PostRepository;
import com.openclassrooms.mddapi.repositories.TopicRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(3)
@RequiredArgsConstructor
public class PostSeeder implements CommandLineRunner {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    @Override
    public void run(String... args) {
        if (postRepository.count() == 0) {
            User author = userRepository.findAll()
                    .stream()
                    .findFirst()
                    .orElse(null);
            Topic topic = topicRepository.findAll()
                    .stream()
                    .findFirst()
                    .orElse(null);

            if (author == null || topic == null) {
                System.out.println("No User or Topic found on database. Seeding ignored.");
                return;
            }

            List<Post> posts = List.of(
                    createPost("Introduction à Java", "Java est un langage orienté objet...", author, topic),
                    createPost("Les bases de Spring Boot", "Spring Boot facilite la création d'APIs...", author, topic),
                    createPost("Comprendre JPA", "JPA est une spécification Java pour...", author, topic)
            );

            postRepository.saveAll(posts);
            System.out.println("Posts successfully initialized");
        }
    }

    private Post createPost(String title, String content, User author, Topic topic) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        post.setTopic(topic);
        return post;
    }
}
