package org.betterJavaApplication.repository.vtubers;

import org.betterJavaApplication.entity.vtubers.TalentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface TalentRepository extends JpaRepository<TalentEntity, String> {


    List<TalentEntity> findByOrganization(String orgId);


    @Query(value = "SELECT * from talent WHERE talent_height = (SELECT MIN(talent_height) FROM talent)", nativeQuery = true)
    Optional<TalentEntity> findShortestTalent();

    @Query("SELECT t FROM TalentEntity t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<TalentEntity> findByName(@Param("name") String name);

}
