package com.saman.sso.business.repository;


import com.saman.sso.domain.refdata.RefDataEntity;

public interface RefDataRepository extends SpringDataJpaRepository<RefDataEntity<Integer>, Integer> {
}
