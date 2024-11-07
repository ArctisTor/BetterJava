package org.betterJavaApplication.repository;

import org.betterJavaApplication.entity.TalentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TalentRepository extends CrudRepository<TalentEntity, String> {

    List<TalentEntity> findByOrganization(String orgName);
}
