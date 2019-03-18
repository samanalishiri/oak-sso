package com.saman.sso.domain.refdata;

import com.saman.sso.domain.AbstractAuditingEntity;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR", discriminatorType = DiscriminatorType.INTEGER, length = 4)
@Table(name = "REF_INFO", schema = "sso")
@Audited
public abstract class RefData extends AbstractAuditingEntity<Integer, String> implements ReadOnlyRefData<Integer> {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REF_INFO_GEN")
    @SequenceGenerator(name = "REF_INFO_GEN", sequenceName = "REF_INFO_SEQ")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
