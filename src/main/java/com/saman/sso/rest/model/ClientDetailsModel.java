package com.saman.sso.rest.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ClientDetailsModel implements ClientDetails {

    private String clientId;
    private Set<String> resourceIds = new HashSet<>();
    private ClientType clientType;
    private String clientSecret;
    private Set<String> scopes = new HashSet<>();
    private Set<String> authorizedGrantTypes = new HashSet<>();
    private Set<String> registeredRedirectUri = new HashSet<>();
    private Integer accessTokenValiditySeconds;
    private HashMap<String, Object> additionalInformation = new HashMap<>();
    private boolean autoApprove = false;
    private List<GrantedAuthority> authorities = new ArrayList<>();
    private Integer refreshTokenValiditySeconds;
    private String clientName;

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return Collections.unmodifiableSet(resourceIds);
    }

    @Override
    public boolean isSecretRequired() {
        return Objects.equals(clientType, ClientType.CONFIDENTIAL);
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return !scopes.isEmpty();
    }

    @Override
    public Set<String> getScope() {
        return Collections.unmodifiableSet(scopes);
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Collections.unmodifiableSet(registeredRedirectUri);
    }

    public void setRegisteredRedirectUri(Set<String> registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(authorities);
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return autoApprove;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    public void setAdditionalInformation(HashMap<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public boolean isAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(boolean autoApprove) {
        this.autoApprove = autoApprove;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String name) {
        additionalInformation.put("clientName", name);
    }

    public void addRedirectUri(String uri) {
        registeredRedirectUri.add(uri);
    }

    public void addScope(String scope) {
        scopes.add(scope);
    }
}
