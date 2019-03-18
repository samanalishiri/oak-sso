package com.saman.sso.business.service;

import com.saman.sso.business.model.UserModel;
import com.saman.sso.domain.UserEntity;

public interface UserService extends CrudService<Long, UserEntity, UserModel>, SearchService<Long, UserEntity, UserModel> {
}
