package com.saman.sso.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClientModel {

    private String id = "";

    private String clientSecret = "";

    private String name = "";

    private String redirectUri = "";

    private ClientType type = ClientType.UNKNOWN;

    private Set<String> scopes = new HashSet<>();

    private Integer accessTokenValiditySeconds = 0;

    private Set<String> grantTypes = new LinkedHashSet<>();

    @JsonIgnore
    public static ClientModel newInstance(ClientDetails clientDetails) {
        ClientModel model = new ClientModel();
        model.setId(clientDetails.getClientId());
        model.setClientSecret(clientDetails.getClientSecret());
        model.setAccessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds());
        model.setGrantTypes(clientDetails.getAuthorizedGrantTypes());
        Optional.ofNullable(clientDetails.getRegisteredRedirectUri())
                .ifPresent(uris -> model.setRedirectUri(uris.stream().collect(Collectors.joining(","))));

        model.setScopes(clientDetails.getScope());
        model.setName(String.format("%s", clientDetails.getAdditionalInformation().get("clientName")));
        model.setType(ClientType.instanceOf(String.valueOf(clientDetails.getAdditionalInformation().get("clientType"))));

        return model;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public Set<String> getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(Set<String> grantTypes) {
        this.grantTypes = grantTypes;
    }
}
