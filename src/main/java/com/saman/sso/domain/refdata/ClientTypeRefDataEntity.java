package com.saman.sso.domain.refdata;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(RefDataGroup.CLIENT_TYPE)
public class ClientTypeRefDataEntity extends RefData {
}
