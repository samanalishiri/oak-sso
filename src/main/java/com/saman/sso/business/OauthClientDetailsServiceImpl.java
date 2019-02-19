package com.saman.sso.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service(OauthClientDetailsServiceImpl.NAME)
public class OauthClientDetailsServiceImpl extends JdbcClientDetailsService {

    public static final String NAME = "oauthClientDetailsServiceImpl";

    @Autowired
    public OauthClientDetailsServiceImpl(DataSource dataSource) {
        super(dataSource);
    }
}
