package com.saman.sso.business.transform;

import com.saman.sso.business.model.AbstractModel;
import com.saman.sso.business.model.RefDataModel;
import com.saman.sso.domain.AbstractAuditingEntity;
import com.saman.sso.domain.refdata.ClientTypeRefDataEntity;
import org.springframework.stereotype.Component;

@Component(ClientTypeRefDataTransformer.NAME)
public class ClientTypeRefDataTransformer extends RefDataTransformer {

    public static final String NAME = "clientTypeRefDataTransformer";

    @Override
    public Class<? extends AbstractModel> getModel() {
        return RefDataModel.class;
    }

    @Override
    public Class<? extends AbstractAuditingEntity> getEntity() {
        return ClientTypeRefDataEntity.class;
    }

    @Override
    public ClientTypeRefDataEntity newEntity() {
        return new ClientTypeRefDataEntity();
    }

}
