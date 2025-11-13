package org.betterJavaApplication.repository.mead;

import java.util.List;

import org.betterJavaApplication.entity.mead.MeadRecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeadRecipeRepository extends JpaRepository<MeadRecipeEntity, String> {
    // You can add custom queries if needed

    // Returns all recipes whose name contains the given string (case-insensitive)
    List<MeadRecipeEntity> findByNameContainingIgnoreCase(String name);
}
