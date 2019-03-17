package com.saman.sso.domain.refinfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(RefInfoGroup.CLIENT_TYPE)
public class ClientTypeRefInfoEntity extends RefInfo {
}
