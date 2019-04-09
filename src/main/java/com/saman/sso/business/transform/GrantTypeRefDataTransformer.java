package com.saman.sso.business.transform;

import com.saman.sso.business.model.AbstractModel;
import com.saman.sso.business.model.RefDataModel;
import com.saman.sso.domain.AbstractAuditingEntity;
import com.saman.sso.domain.refdata.GrantTypeRefDataEntity;
import org.springframework.stereotype.Component;

@Component(GrantTypeRefDataTransformer.NAME)
public class GrantTypeRefDataTransformer extends RefDataTransformer {

    public static final String NAME = "grantTypeRefDataTransformer";

    @Override
    public Class<? extends AbstractModel> getModel() {
        return RefDataModel.class;
    }

    @Override
    public Class<? extends AbstractAuditingEntity> getEntity() {
        return GrantTypeRefDataEntity.class;
    }

    @Override
    public GrantTypeRefDataEntity newEntity() {
        return new GrantTypeRefDataEntity();
    }

}
