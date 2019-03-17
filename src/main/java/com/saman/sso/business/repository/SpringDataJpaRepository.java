package com.saman.sso.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.history.RevisionRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface SpringDataJpaRepository<E, ID extends Serializable> extends RevisionRepository<E, ID, Integer>, JpaRepository<E, ID>, CrudRepository<E, ID> {
}
