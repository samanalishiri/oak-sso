package com.saman.sso.business.service;

import com.saman.sso.business.model.AbstractModel;
import com.saman.sso.business.repository.SpringDataJpaRepository;
import com.saman.sso.domain.AbstractAuditingEntity;
import com.saman.sso.domain.refdata.ReadOnlyRefData;
import com.saman.sso.domain.refdata.RefData;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public abstract class AbstractBusinessService<I extends Serializable,
        E extends AbstractAuditingEntity<I, String>,
        M extends AbstractModel<I>,
        R extends SpringDataJpaRepository<E, I>,
        S extends CrudService<I, E, M> & SearchService<I, E, M>>
        implements CrudService<I, E, M>, SearchService<I, E, M> {

    private S service;

    @Override
    public I save(M m) {
        return service.save(m);
    }

    @Override
    public Optional<M> findById(I id) {
        return service.findById(id);
    }

    @Override
    public void edit(M m) {
        service.edit(m);
    }

    @Override
    public void deleteById(I id) {
        service.deleteById(id);
    }

    @Override
    public Optional<Collection<M>> findAll() {
        return service.findAll();
    }

    @Override
    public Optional<Collection<ReadOnlyRefData<Integer>>> findAllRefData(Class<? extends RefData> c) {
        return service.findAllRefData(c);
    }
}
