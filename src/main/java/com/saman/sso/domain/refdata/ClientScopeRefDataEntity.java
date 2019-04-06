package com.saman.sso.domain.refdata;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(RefDataGroup.CLIENT_SCOPE)
public class ClientScopeRefDataEntity extends RefDataEntity<Integer> {
}
