package org.betterJavaApplication.repository.mead;

import org.betterJavaApplication.entity.mead.MeadRecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeadRecipeRepository extends JpaRepository<MeadRecipeEntity, String> {
    // You can add custom queries if needed
}
