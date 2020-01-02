package com.saman.sso.rest;

import com.saman.sso.rest.model.ClientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.saman.sso.rest.model.ClientDetailsModel.newInstance;
import static com.saman.sso.rest.model.ClientDetailsModel.transform;

@RestController
@RequestMapping(value = "client", produces = "application/json")
public class ClientResource {

    @Autowired
    private JdbcClientDetailsService clientDetailsService;

    @PostMapping(value = "save")
    public ResponseEntity<ClientModel> save(@RequestBody ClientModel model) {
        clientDetailsService.addClientDetails(newInstance(model));
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @GetMapping(value = "find/all")
    public ResponseEntity<List<ClientModel>> findAll() {
        List<ClientModel> models = clientDetailsService.listClientDetails().stream()
                .map(ClientModel::newInstance)
                .collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @GetMapping(value = "find/{id}")
    public ResponseEntity<ClientModel> findById(@PathVariable("id") String id) {
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(id);
        return ResponseEntity.ok(ClientModel.newInstance(clientDetails));
    }

    @PutMapping(value = "edit")
    @ResponseStatus(value = HttpStatus.OK)
    public void edit(@RequestBody ClientModel model) {
        clientDetailsService.updateClientDetails(transform(model));
    }

}
