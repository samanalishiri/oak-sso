package com.saman.sso.business.transform;

import com.saman.sso.business.model.AbstractModel;
import com.saman.sso.business.model.RefDataModel;
import com.saman.sso.domain.AbstractAuditingEntity;
import com.saman.sso.domain.refdata.ClientScopeRefDataEntity;
import org.springframework.stereotype.Component;

@Component(ClientScopeRefDataTransformer.NAME)
public class ClientScopeRefDataTransformer extends RefDataTransformer {

    public static final String NAME = "clientScopeRefDataTransformer";

    @Override
    public Class<? extends AbstractModel> getModel() {
        return RefDataModel.class;
    }

    @Override
    public Class<? extends AbstractAuditingEntity> getEntity() {
        return ClientScopeRefDataEntity.class;
    }

    @Override
    public ClientScopeRefDataEntity newEntity() {
        return new ClientScopeRefDataEntity();
    }

}
