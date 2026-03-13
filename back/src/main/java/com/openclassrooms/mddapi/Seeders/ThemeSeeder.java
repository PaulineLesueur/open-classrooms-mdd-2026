package com.openclassrooms.mddapi.Seeders;

import com.openclassrooms.mddapi.entities.Theme;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class ThemeSeeder implements CommandLineRunner {
    private final ThemeRepository themeRepository;

    @Override
    public void run(String... args) {
        if(themeRepository.count() == 0) {
            List<Theme> themes = List.of(
                createTheme("Java", "Langage de programmation orienté objet robuste et portable"),
                createTheme("Python", "Langage polyvalent idéal pour la data science et l'IA"),
                createTheme("JavaScript", "Langage incontournable du développement web frontend"),
                createTheme("Spring Boot", "Framework Java pour construire des APIs REST"),
                createTheme("Angular", "Framework JavaScript frontend maintenu par Google")
            );
            themeRepository.saveAll(themes);
            System.out.println("Themes successfully initialized");
        } else {
            System.out.println("Themes already exist, no seeding is necessary");
        }
    }

    private Theme createTheme(String name, String description) {
        Theme theme = new Theme();
        theme.setName(name);
        theme.setDescription(description);
        return theme;
    }
}
