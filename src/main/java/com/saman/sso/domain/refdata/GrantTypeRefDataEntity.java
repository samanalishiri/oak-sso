package com.saman.sso.domain.refdata;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(RefDataGroup.GRANT_TYPE)
public class GrantTypeRefDataEntity extends RefDataEntity<Integer> {
}
