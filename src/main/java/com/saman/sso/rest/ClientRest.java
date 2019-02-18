package com.saman.sso.rest;

import com.saman.sso.rest.model.BasicClientInfoModel;
import com.saman.sso.rest.model.ClientDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/client", produces = "application/json")
public class ClientRest {

    @Autowired
    private ClientRegistrationService clientRegistrationService;

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<Object> save(@RequestBody BasicClientInfoModel model) {

        ClientDetailsModel app = new ClientDetailsModel();
        app.setName(model.getClientName());
        app.addRedirectUri(model.getRedirectUri());
        app.setClientType(model.getClientType());
        app.setClientId(UUID.randomUUID().toString());
        app.setClientSecret(UUID.randomUUID().toString());
        app.setAccessTokenValidity(3000);
        app.addScope("read_profile");
        app.addScope("read_contacts");

        clientRegistrationService.addClientDetails(app);

        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }
}
