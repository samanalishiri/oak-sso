package com.saman.sso.business.transform;

import com.saman.sso.business.model.AbstractModel;
import com.saman.sso.business.model.AuthorityModel;
import com.saman.sso.domain.AbstractAuditingEntity;
import com.saman.sso.domain.AuthorityEntity;
import org.springframework.stereotype.Component;

@Component(AuthorityTransformer.NAME)
public class AuthorityTransformer extends Transformer<Long, AuthorityEntity, AuthorityModel> {
    public static final String NAME = "authorityConverter";

    @Override
    public Class<? extends AbstractAuditingEntity> getEntity() {
        return AuthorityEntity.class;
    }

    @Override
    public Class<? extends AbstractModel> getModel() {
        return AuthorityModel.class;
    }

    @Override
    public void transformFromEntityToModel(AuthorityEntity input, AuthorityModel output, int deep, String... relations) {
        output.setId(input.getId());
        output.setAuthority(input.getAuthority());
    }

    @Override
    public void transformFromModelToEntity(AuthorityModel input, AuthorityEntity output, int deep, String... relations) {
        output.setId(input.getId());
        output.setAuthority(input.getAuthority());
    }

    @Override
    public AuthorityEntity newEntity() {
        return new AuthorityEntity();
    }

    @Override
    public AuthorityModel newModel() {
        return new AuthorityModel();
    }
}
