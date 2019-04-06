package com.saman.sso.business.service;

import com.saman.sso.business.helper.MessageSourceHelper;
import com.saman.sso.business.model.AbstractModel;
import com.saman.sso.business.repository.SpringDataJpaRepository;
import com.saman.sso.business.transform.Transformer;
import com.saman.sso.domain.AbstractAuditingEntity;
import com.saman.sso.util.GenericUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractService<I extends Serializable,
        E extends AbstractAuditingEntity<I, String>,
        M extends AbstractModel<I>,
        R extends SpringDataJpaRepository<E, I>>
        implements CrudService<I, E, M>, ReadOnlyService<I, E, M> {

    protected final Class<? extends AbstractModel> model = (Class<? extends AbstractModel>) GenericUtils.extract(this.getClass(), 1);
    protected final Class<? extends AbstractAuditingEntity> entity = (Class<? extends AbstractAuditingEntity>) GenericUtils.extract(this.getClass(), 2);
    protected R repository;
    protected Transformer<I, E, M> transformer;

    @Autowired
    protected MessageSourceHelper messageSource;

    public AbstractService(R repository, Transformer<I, E, M> transformer) {
        this.repository = repository;
        this.transformer = transformer;
    }

    @Override
    public I save(M m) {
        beforeSave(m);
        E e = repository.save(transformer.transform(m));
        Objects.requireNonNull(e, messageSource.getMessage("error.service.save.failed", entity.getSimpleName()));

        return e.getId();
    }

    protected void beforeSave(M m) {

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
}
