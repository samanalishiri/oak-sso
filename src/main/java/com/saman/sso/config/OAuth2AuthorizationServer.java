package com.saman.sso.config;

import com.saman.sso.business.OauthClientDetailsServiceImpl;
import com.saman.sso.business.service.CustomPasswordEncoder;
import com.saman.sso.business.service.UserDetailsServiceImpl;
import com.saman.sso.component.AuthTokenEnhancer;
import com.saman.sso.component.CustomClaimAccessTokenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Arrays;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier(OauthClientDetailsServiceImpl.NAME)
    private JdbcClientDetailsService oauthClientDetailsService;

    @Autowired
    @Qualifier(UserDetailsServiceImpl.BEAN_NAME)
    private UserDetailsService userDetailsService;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private AuthTokenEnhancer authTokenEnhancer;

    @Autowired
    private CustomClaimAccessTokenConverter customClaimAccessTokenConverter;

    @Autowired
    @Qualifier(CustomPasswordEncoder.NAME)
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(oauthClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenServices(tokenServices())
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(passwordEncoder)
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource(applicationProperties.getAuth().getKeyStore()),
                applicationProperties.getAuth().getKeyPassword().toCharArray());

        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("server"));
        converter.setAccessTokenConverter(customClaimAccessTokenConverter);
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(authTokenEnhancer, accessTokenConverter()));
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);

        defaultTokenServices.setReuseRefreshToken(false);
        defaultTokenServices.setAccessTokenValiditySeconds(applicationProperties.getAuth().getDefaultAccessTokenTimeout());
        defaultTokenServices.setRefreshTokenValiditySeconds(applicationProperties.getAuth().getDefaultRefreshTokenTimeout());
        defaultTokenServices.setClientDetailsService(oauthClientDetailsService);

        defaultTokenServices.setAuthenticationManager(authentication -> {
            UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

            PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(
                    authentication.getName(),
                    authentication.getCredentials(),
                    userDetails.getAuthorities());

            token.setDetails(userDetails);
            return token;
        });

        return defaultTokenServices;
    }

}