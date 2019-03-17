package com.saman.sso.domain;

import org.hibernate.annotations.Type;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "USERS", schema = "SSO")
public class UserEntity extends AbstractAuditingEntity<Long, String> implements UserDetails {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_GEN")
    @SequenceGenerator(name = "USERS_GEN", sequenceName = "USERS_SEQ")
    private Long id;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "PASSWD", nullable = false)
    private String password;

    @Column(name = "EMAIL", unique = true, nullable = true)
    private String email;

    @Column(name = "ENABLED")
    @Type(type = "boolean")
    private boolean enabled = true;

    @Column(name = "NON_EXPIRED")
    @Type(type = "boolean")
    private boolean accountNonExpired = true;

    @Column(name = "NON_LOCKED")
    @Type(type = "boolean")
    private boolean accountNonLocked = true;

    @Column(name = "PASSWORD_NON_EXPIRED")
    @Type(type = "boolean")
    private boolean credentialsNonExpired = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_AUTHORITY",
            joinColumns = @JoinColumn(name = "USERS_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID"))
    private List<AuthorityEntity> authorities;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public List<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

}
