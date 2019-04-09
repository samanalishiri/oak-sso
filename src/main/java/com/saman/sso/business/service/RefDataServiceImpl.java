package com.saman.sso.business.service;

import com.saman.sso.business.model.RefDataModel;
import com.saman.sso.business.repository.RefDataRepository;
import com.saman.sso.business.transform.ClientScopeRefDataTransformer;
import com.saman.sso.business.transform.ClientTypeRefDataTransformer;
import com.saman.sso.business.transform.GrantTypeRefDataTransformer;
import com.saman.sso.business.transform.RefDataTransformer;
import com.saman.sso.config.ApplicationContextBean;
import com.saman.sso.domain.refdata.ClientScopeRefDataEntity;
import com.saman.sso.domain.refdata.ClientTypeRefDataEntity;
import com.saman.sso.domain.refdata.GrantTypeRefDataEntity;
import com.saman.sso.domain.refdata.RefDataEntity;
import com.saman.sso.domain.refdata.RefDataEnum;
import com.saman.sso.util.TripleMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.saman.sso.business.transform.Transformer.ZERO_DEEP;

@Service(RefDataServiceImpl.BEAN_NAME)
public class RefDataServiceImpl implements RefDataService<Integer, RefDataEntity<Integer>, RefDataModel> {

    public static final String BEAN_NAME = "refDataServiceImpl";

    private static final Logger LOGGER = LoggerFactory.getLogger(RefDataServiceImpl.class);

    private TripleMap<RefDataEnum, String, RefDataEntity> refDataMapping = new TripleMap<>();

    @Autowired
    private RefDataRepository repository;

    @Autowired
    private ApplicationContextBean applicationContextBean;

    public RefDataServiceImpl() {
        refDataMapping.put(RefDataEnum.CLIENT_TYPE, ClientTypeRefDataTransformer.NAME, new ClientTypeRefDataEntity());
        refDataMapping.put(RefDataEnum.CLIENT_SCOPE, ClientScopeRefDataTransformer.NAME, new ClientScopeRefDataEntity());
        refDataMapping.put(RefDataEnum.GRANT_TYPE, GrantTypeRefDataTransformer.NAME, new GrantTypeRefDataEntity());
    }

    @Override
    public Optional<Collection<RefDataModel>> findAllRefData(RefDataEnum group) {
        return Optional.ofNullable(
                ApplicationContextBean.getBean(refDataMapping.get(group).getV1(), RefDataTransformer.class).transformFromEntitiesToModels(
                        () -> repository.findAll(Example.of(refDataMapping.get(group).getV2())),
                        ZERO_DEEP));

    }

}
