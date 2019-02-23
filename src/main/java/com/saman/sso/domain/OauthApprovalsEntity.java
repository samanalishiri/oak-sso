package com.saman.sso.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "oauth_approvals", schema = "sso")
public class OauthApprovalsEntity {

    private Long id;
    private String userid;
    private String clientid;
    private String scope;
    private String status;
    private Timestamp expiresat;
    private Timestamp lastmodifiedat;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USERID")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "CLIENTID")
    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    @Basic
    @Column(name = "SCOPE")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "EXPIRESAT")
    public Timestamp getExpiresat() {
        return expiresat;
    }

    public void setExpiresat(Timestamp expiresat) {
        this.expiresat = expiresat;
    }

    @Basic
    @Column(name = "LASTMODIFIEDAT")
    public Timestamp getLastmodifiedat() {
        return lastmodifiedat;
    }

    public void setLastmodifiedat(Timestamp lastmodifiedat) {
        this.lastmodifiedat = lastmodifiedat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OauthApprovalsEntity that = (OauthApprovalsEntity) o;
        return Objects.equals(userid, that.userid) &&
                Objects.equals(clientid, that.clientid) &&
                Objects.equals(scope, that.scope) &&
                Objects.equals(status, that.status) &&
                Objects.equals(expiresat, that.expiresat) &&
                Objects.equals(lastmodifiedat, that.lastmodifiedat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, clientid, scope, status, expiresat, lastmodifiedat);
    }
}
