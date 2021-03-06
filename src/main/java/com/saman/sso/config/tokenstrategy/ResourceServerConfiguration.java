package com.saman.sso.config.tokenstrategy;

import com.saman.sso.config.profile.Token;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@Token
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource-server-rest-api";
    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
    private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
    private static final String CLIENT_PATTERN = "/client/**";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers(CLIENT_PATTERN)
                .antMatchers("/refdata/**")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, CLIENT_PATTERN).access(SECURED_WRITE_SCOPE)
                .antMatchers(HttpMethod.GET, CLIENT_PATTERN).access(SECURED_READ_SCOPE)
                .antMatchers(HttpMethod.POST, "/refdata/**").access(SECURED_WRITE_SCOPE)
                .anyRequest().access(SECURED_READ_SCOPE)
                .and()
                .formLogin()
                .defaultSuccessUrl("/")
                .permitAll()
        ;
    }
}