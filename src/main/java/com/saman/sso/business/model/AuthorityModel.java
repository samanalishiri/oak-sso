package com.saman.sso.business.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorityModel extends AbstractModel<Long> implements GrantedAuthority {

    private Long id;

    private String authority;

    private String description;

    private boolean enabled = true;

    private AuthorityModel parent;

    private List<AuthorityModel> children;

    private List<UserModel> users;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public AuthorityModel getParent() {
        return parent;
    }

    public void setParent(AuthorityModel parent) {
        this.parent = parent;
    }

    public List<AuthorityModel> getChildren() {
        return children;
    }

    public void setChildren(List<AuthorityModel> children) {
        this.children = children;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
