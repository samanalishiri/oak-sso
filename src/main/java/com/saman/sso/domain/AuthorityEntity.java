package com.saman.sso.domain;

/**
 * this entity store roles
 */

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Authority", schema = "SSO")
@Audited
public class AuthorityEntity extends AbstractAuditingEntity<Long, String> implements GrantedAuthority {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHORITY_GEN")
    @SequenceGenerator(name = "AUTHORITY_GEN", sequenceName = "AUTHORITY_SEQ")
    private Long id;

    @Column(name = "AUTHORITY", nullable = false)
    private String authority;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ENABLED")
    @Type(type = "boolean")
    private boolean enabled = true;

    @NotAudited
    @ManyToMany(mappedBy = "authorities", cascade = CascadeType.ALL)
    private List<UserEntity> users;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
