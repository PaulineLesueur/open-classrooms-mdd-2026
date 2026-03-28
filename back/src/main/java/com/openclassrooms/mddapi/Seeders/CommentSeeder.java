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
            List<Post> posts = postRepository.findAll();

            if (author == null || posts.isEmpty()) {
                System.out.println("No User or Post found in database. Comment seed ignored.");
                return;
            }

            Post firstPost  = posts.get(0);
            Post secondPost = posts.size() > 1 ? posts.get(1) : firstPost;
            Post thirdPost  = posts.size() > 2 ? posts.get(2) : firstPost;

            List<Comment> comments = List.of(
                createComment("Excellent article ! Le point sur le pattern matching avec les sealed classes est particulièrement bien expliqué. J'utilisais encore l'ancienne syntaxe instanceof, je vais migrer ça dès demain.", author, firstPost),
                createComment("Merci pour cette synthèse claire. Une question : est-ce que les records peuvent implémenter des interfaces ? Je n'ai pas trouvé de réponse définitive dans la doc officielle.", author, firstPost),
                createComment("Très bon résumé de Java 21. J'ajouterais que les virtual threads (Project Loom), aussi disponibles depuis Java 21, méritent un article à part entière. Le gain de perfs sur les applis I/O-bound est impressionnant.", author, firstPost),
                createComment("Parfait timing, je suis justement en train de migrer vers Spring Boot 3. La partie sur le stockage du UUID dans le subject du JWT m'a évité un bug que j'allais reproduire. Merci !", author, secondPost),
                createComment("Est-ce que vous avez un exemple avec refresh tokens ? La gestion de l'expiration côté frontend est souvent le point le plus délicat à gérer proprement.", author, secondPost),
                createComment("Bonne introduction à asyncio. Je préciserais que asyncio.gather() propage la première exception levée et annule les autres tâches par défaut. Pour un comportement différent, asyncio.gather(*tasks, return_exceptions=True) est utile.", author, thirdPost)
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
