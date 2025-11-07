package org.betterJavaApplication.repository.vtubers;

import org.betterJavaApplication.entity.vtubers.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, String> {
    Optional<OrganizationEntity> findByName(String name);
}
