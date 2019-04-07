package com.saman.sso.rest;

import com.saman.sso.business.model.RefDataModel;
import com.saman.sso.business.service.RefDataService;
import com.saman.sso.domain.refdata.RefDataEntity;
import com.saman.sso.domain.refdata.RefDataEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/refdata", produces = "application/json")
public class RefDataResource {

    @Autowired
    private RefDataService<Integer, RefDataEntity<Integer>, RefDataModel> service;

    @GetMapping(value = "find/all/{group}")
    public ResponseEntity<Collection<RefDataModel>> findAll(@PathVariable("group") RefDataEnum group) {
        Optional<Collection<RefDataModel>> dataHolder = service.findAllRefData(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(dataHolder.get());
    }

}
