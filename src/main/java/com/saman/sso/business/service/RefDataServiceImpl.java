package com.saman.sso.business.service;


import com.saman.sso.business.model.RefDataModel;
import com.saman.sso.business.repository.RefDataRepository;
import com.saman.sso.business.transform.ClientScopeRefDataTransformer;
import com.saman.sso.business.transform.ClientTypeRefDataTransformer;
import com.saman.sso.business.transform.RefDataTransformer;
import com.saman.sso.domain.refdata.ClientScopeRefDataEntity;
import com.saman.sso.domain.refdata.ClientTypeRefDataEntity;
import com.saman.sso.domain.refdata.RefDataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.saman.sso.business.transform.Transformer.ZERO_DEEP;

@Service(RefDataServiceImpl.BEAN_NAME)
public class RefDataServiceImpl implements RefDataService<Integer, RefDataEntity<Integer>, RefDataModel> {

    public static final String BEAN_NAME = "refDataServiceImpl";

    private static final Logger LOGGER = LoggerFactory.getLogger(RefDataServiceImpl.class);

    private Map<Class<? extends RefDataEntity>, RefDataTransformer> transformerRegistry = new HashMap<>();
    @Autowired
    private RefDataRepository repository;

    public RefDataServiceImpl() {
        transformerRegistry.put(ClientTypeRefDataEntity.class, new ClientTypeRefDataTransformer());
        transformerRegistry.put(ClientScopeRefDataEntity.class, new ClientScopeRefDataTransformer());
    }

    @Override
    public Optional<Collection<RefDataModel>> findAllRefData(Class<? extends RefDataEntity<Integer>> c) {

        try {
            RefDataTransformer transformer = transformerRegistry.get(c).clone();
            return Optional.ofNullable(Collections.unmodifiableList(transformer.transformFromEntitiesToModels(repository::findAll, ZERO_DEEP)));

        } catch (CloneNotSupportedException e) {
            LOGGER.error(e.getMessage());
        }

        return Optional.empty();
    }

}
