package com.saman.sso.business.model;

import com.saman.sso.domain.refdata.ReadOnlyRefData;
import org.springframework.stereotype.Component;

@Component
public class RefDataModel extends AbstractModel<Integer> implements ReadOnlyRefData<Integer> {

    private Integer id;

    private String name;

    @Override
    public Integer getId() {
        return id;
    }

    public RefDataModel setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
