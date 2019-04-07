package com.saman.sso.business.transform;

import com.saman.sso.business.model.RefDataModel;
import com.saman.sso.domain.refdata.RefDataEntity;

public abstract class RefDataTransformer extends Transformer<Integer, RefDataEntity<Integer>, RefDataModel> {

    public static final String NAME = "clientTypeRefDataTransformer";

    @Override
    public void transformFromEntityToModel(RefDataEntity<Integer> input, RefDataModel output, int deep, String... relations) {
        output.setId(input.getId());
        output.setName(input.getName());
    }

    @Override
    public void transformFromModelToEntity(RefDataModel input, RefDataEntity<Integer> output, int deep, String... relations) {
        output.setId(input.getId());
        output.setName(input.getName());
    }

    @Override
    public RefDataModel newModel() {
        return new RefDataModel();
    }

}
