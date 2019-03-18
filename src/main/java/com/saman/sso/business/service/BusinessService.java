package com.saman.sso.business.service;

import com.saman.sso.business.model.AbstractModel;
import com.saman.sso.business.repository.SpringDataJpaRepository;
import com.saman.sso.business.transform.Transformer;
import com.saman.sso.domain.AbstractAuditingEntity;
import com.saman.sso.domain.refdata.ReadOnlyRefData;
import com.saman.sso.domain.refdata.RefData;
import com.saman.sso.util.GenericUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BusinessService<I extends Serializable,
        E extends AbstractAuditingEntity<I, String>,
        M extends AbstractModel<I>,
        R extends SpringDataJpaRepository<E, I>>
        implements CrudService<I, E, M>, SearchService<I, E, M> {

    private final Class<E> entity = (Class<E>) GenericUtils.extract(this.getClass(), 1);

    private final Class<M> model = (Class<M>) GenericUtils.extract(this.getClass(), 2);

    private R repository;

    private Transformer<I, E, M> transformer;

    private Consumer<M> beforeSave;

    private BiConsumer<E, M> afterSave;

    public BusinessService(R repository, Transformer<I, E, M> transformer) {
        this.repository = repository;
        this.transformer = transformer;
    }

    public void setBeforeSave(Consumer<M> beforeSave) {
        this.beforeSave = beforeSave;
    }

    @Override
    public I save(M m) {
        beforeSave.accept(m);
        E e = repository.save(transformer.transform(m));
        afterSave.accept(e, m);
        return e.getId();
    }


    @Override
    public Optional<M> findById(I id) {
        return Optional.empty();
    }

    @Override
    public void edit(M m) {

    }

    @Override
    public void deleteById(I id) {

    }

    @Override
    public Optional<Collection<M>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<ReadOnlyRefData<Integer>>> findAllRefData(Class<? extends RefData> c) {
        return Optional.empty();
    }
}
