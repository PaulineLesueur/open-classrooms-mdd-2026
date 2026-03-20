package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Theme} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations.
 * </p>
 */
@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {}
