package com.saman.sso.business.service;

import com.saman.sso.business.model.AbstractModel;
import com.saman.sso.business.model.RefDataModel;
import com.saman.sso.domain.AbstractAuditingEntity;
import com.saman.sso.domain.refdata.RefDataEnum;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface RefDataService<I extends Serializable, E extends AbstractAuditingEntity<I, String>, M extends AbstractModel<I>> {

    Optional<Collection<RefDataModel>> findAllRefData(RefDataEnum group);

}
