package com.saman.sso.business.service;

import com.saman.sso.business.model.AbstractModel;
import com.saman.sso.domain.AbstractAuditingEntity;

import java.io.Serializable;
import java.util.Optional;

public interface CrudService<I extends Serializable, E extends AbstractAuditingEntity<I, String>, M extends AbstractModel<I>> {

    I save(M m);

    Optional<M> findById(I id);

    void edit(M m);

    void deleteById(I id);

}
