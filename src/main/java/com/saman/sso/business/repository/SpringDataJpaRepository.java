package com.saman.sso.business.repository;

import com.saman.sso.domain.AbstractAuditingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.history.RevisionRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface SpringDataJpaRepository<E extends AbstractAuditingEntity<ID, String>, ID extends Serializable> extends RevisionRepository<E, ID, Integer>, JpaRepository<E, ID>, CrudRepository<E, ID> {
}
