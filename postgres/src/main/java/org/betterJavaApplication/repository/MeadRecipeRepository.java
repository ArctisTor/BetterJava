package org.betterJavaApplication.repository;

import org.betterJavaApplication.entity.MeadRecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeadRecipeRepository extends JpaRepository<MeadRecipeEntity, Long> {
    // You can add custom queries if needed
}
