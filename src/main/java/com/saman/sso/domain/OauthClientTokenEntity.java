package com.saman.sso.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "oauth_client_token", schema = "sso")
public class OauthClientTokenEntity {
    private String tokenId;
    private byte[] token;
    private String authenticationId;
    private String userName;
    private String clientId;

    @Basic
    @Column(name = "TOKEN_ID")
    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    @Basic
    @Column(name = "TOKEN")
    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    @Id
    @Column(name = "AUTHENTICATION_ID")
    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    @Basic
    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "CLIENT_ID")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OauthClientTokenEntity that = (OauthClientTokenEntity) o;
        return Objects.equals(tokenId, that.tokenId) &&
                Arrays.equals(token, that.token) &&
                Objects.equals(authenticationId, that.authenticationId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(tokenId, authenticationId, userName, clientId);
        result = 31 * result + Arrays.hashCode(token);
        return result;
    }
}
