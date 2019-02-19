package com.saman.sso.business.repository;


import com.saman.sso.domain.UserEntity;

public interface UserRepository extends SpringDataJpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
