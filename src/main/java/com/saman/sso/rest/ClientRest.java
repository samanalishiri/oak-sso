package com.saman.sso.rest;

import com.saman.sso.rest.model.ClientDetailsModel;
import com.saman.sso.rest.model.ClientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.saman.sso.util.NumberUtils.generateUUID;

@RestController
@RequestMapping(value = "/", produces = "application/json")
public class ClientRest {

    @Autowired
    private ClientRegistrationService oauthClientDetailsService;

    @RequestMapping(value = "client/save", method = {RequestMethod.POST})
    public ResponseEntity<Object> save(@RequestBody ClientModel model) {

        ClientDetailsModel app = new ClientDetailsModel();
        app.setClientName(model.getName());
        app.addRedirectUri(model.getRedirectUri());
        app.setClientType(model.getType());
        app.setClientId(generateUUID());
        app.setClientSecret(generateUUID());
        app.setAccessTokenValiditySeconds(model.getAccessTokenValiditySeconds());
        app.setScopes(model.getScopes());
        app.setAuthorizedGrantTypes(model.getAuthorizedGrantTypes());

        oauthClientDetailsService.addClientDetails(app);

        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

}
