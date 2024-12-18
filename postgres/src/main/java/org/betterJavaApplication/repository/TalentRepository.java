package org.betterJavaApplication.repository;

import org.betterJavaApplication.entity.TalentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TalentRepository extends JpaRepository<TalentEntity, String> {

    List<TalentEntity> findByOrganization(String orgName);

    @Query(value = "SELECT * from talent WHERE talent_height = (SELECT MIN(talent_height) FROM talent)", nativeQuery = true)
    Optional<TalentEntity> findShortestTalent();

}
