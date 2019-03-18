package com.saman.sso.domain;

import org.hibernate.envers.RevisionListener;

import static com.saman.sso.util.SecurityUtils.getCurrentUsername;

public class AuditRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity.class
                .cast(revisionEntity)
                .setUsername(getCurrentUsername());
    }

}
