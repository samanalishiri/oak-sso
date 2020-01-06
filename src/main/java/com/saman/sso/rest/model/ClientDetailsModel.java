package com.saman.sso.rest.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.saman.sso.util.NumberUtils.generateUUID;
import static java.util.Optional.ofNullable;

public class ClientDetailsModel extends BaseClientDetails {

    private String clientName;
    private ClientType clientType;

    public ClientDetailsModel() {
        if (Objects.isNull(super.getRegisteredRedirectUri())) {
            super.setRegisteredRedirectUri(new LinkedHashSet<>());
        }
    }

    public static ClientDetails newInstance(ClientModel model) {
        ClientDetailsModel app = new ClientDetailsModel();
        app.setClientName(model.getName());
        app.addRedirectUri(model.getRedirectUri());
        app.setClientType(ClientType.instanceOf(model.getType()));
        app.setClientId(generateUUID());
        app.setClientSecret(generateUUID());
        app.setAccessTokenValiditySeconds(model.getAccessTokenValiditySeconds());
        app.setScope(model.getScopes());
        app.setAuthorizedGrantTypes(model.getGrantTypes());
        return app;
    }

    public static ClientDetails transform(ClientModel model) {
        ClientDetailsModel app = new ClientDetailsModel();
        app.setClientName(model.getName());
        app.addRedirectUri(model.getRedirectUri());
        app.setClientType(ClientType.instanceOf(model.getType()));
        app.setClientId(model.getId());
        app.setClientSecret(model.getClientSecret());
        app.setAccessTokenValiditySeconds(model.getAccessTokenValiditySeconds());
        app.setScope(model.getScopes());
        app.setAuthorizedGrantTypes(model.getGrantTypes());
        return app;
    }

    @Override
    public boolean isSecretRequired() {
        return super.isSecretRequired() && Objects.equals(clientType, ClientType.CONFIDENTIAL);
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return super.getRegisteredRedirectUri();
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String name) {
        this.clientName = name;
        addAdditionalInformation("clientName", name);
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
        addAdditionalInformation("clientType", clientType);
    }

    public void addRedirectUri(String redirectUri) {
        getRegisteredRedirectUri().add(redirectUri);
    }
}
