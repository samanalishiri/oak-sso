package com.saman.sso.business.transform;

import com.saman.sso.business.model.UserModel;
import com.saman.sso.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(UserTransformer.NAME)
public class UserTransformer extends Transformer<Long, UserEntity, UserModel> {

    public static final String NAME = "userConverter";

    @Autowired
    private AuthorityTransformer authorityTransformer;

    @Override
    public void transformFromEntityToModel(UserEntity input, UserModel output, int deep, String... relations) {
        output.setId(input.getId());
        output.setUsername(input.getUsername());
        output.setPassword(input.getPassword());
        output.setEmail(input.getEmail());
        output.setAuthorities(authorityTransformer.transformFromEntitiesToModels(input.getAuthorities(), 1));
    }

    @Override
    public void transformFromModelToEntity(UserModel input, UserEntity output, int deep, String... relations) {
        output.setId(input.getId());
        output.setUsername(input.getUsername());
        output.setPassword(input.getPassword());
        output.setEmail(input.getEmail());
        output.setAuthorities(authorityTransformer.transformFromModelsToEntities(input.getAuthorities(), 1));
    }

    @Override
    public UserEntity newEntity() {
        return new UserEntity();
    }

    @Override
    public UserModel newModel() {
        return new UserModel();
    }
}
