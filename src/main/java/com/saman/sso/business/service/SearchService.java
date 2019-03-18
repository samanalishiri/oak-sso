package com.saman.sso.business.service;

import com.saman.sso.business.model.AbstractModel;
import com.saman.sso.domain.AbstractAuditingEntity;
import com.saman.sso.domain.refdata.ReadOnlyRefData;
import com.saman.sso.domain.refdata.RefData;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface SearchService<I extends Serializable, E extends AbstractAuditingEntity<I, String>, M extends AbstractModel<I>> {

    Optional<Collection<M>> findAll();

    Optional<Collection<ReadOnlyRefData<Integer>>> findAllRefData(Class<? extends RefData> c);

}
