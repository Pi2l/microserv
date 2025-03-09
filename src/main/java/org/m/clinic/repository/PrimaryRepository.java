package org.m.clinic.repository;

import org.m.clinic.model.HasIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PrimaryRepository<Entity extends HasIdentifier, IdType>
    extends JpaRepository<Entity, IdType>, JpaSpecificationExecutor<Entity> {
}
