package com.openclassrooms.mddapi.Seeders;

import com.openclassrooms.mddapi.entities.Article;
import com.openclassrooms.mddapi.entities.Theme;
import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleSeeder implements CommandLineRunner {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;

    @Override
    public void run(String... args) {
        if (articleRepository.count() == 0) {
            User author = userRepository.findAll()
                    .stream()
                    .findFirst()
                    .orElse(null);
            Theme theme = themeRepository.findAll()
                    .stream()
                    .findFirst()
                    .orElse(null);

            if (author == null || theme == null) {
                System.out.println("No User nor Theme found on database. Seeding ignored.");
                return;
            }

            List<Article> articles = List.of(
                    createArticle("Introduction à Java", "Java est un langage orienté objet...", author, theme),
                    createArticle("Les bases de Spring Boot", "Spring Boot facilite la création d'APIs...", author, theme),
                    createArticle("Comprendre JPA", "JPA est une spécification Java pour...", author, theme)
            );

            articleRepository.saveAll(articles);
            System.out.println("Articles successfully initialized");
        }
    }

    private Article createArticle(String title, String content, User author, Theme theme) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(author);
        article.setTheme(theme);
        return article;
    }
}