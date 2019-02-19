package com.saman.sso.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:custom-info.properties")
@ConfigurationProperties
public class ApplicationProperties {

    private Auth auth;

    private Endpoint endpoint;

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public static class Auth {

        private String resourceId;

        private String keyStore;

        private String keyPassword;

        private Integer defaultAccessTokenTimeout;

        private Integer defaultRefreshTokenTimeout;

        private Integer failedLoginAttemptAccountLockTimeout;

        private Integer maxFailedLoginAttemptsForAccountLock;

        public String getResourceId() {
            return resourceId;
        }

        public void setResourceId(String resourceId) {
            this.resourceId = resourceId;
        }

        public String getKeyStore() {
            return keyStore;
        }

        public void setKeyStore(String keyStore) {
            this.keyStore = keyStore;
        }

        public String getKeyPassword() {
            return keyPassword;
        }

        public void setKeyPassword(String keyPassword) {
            this.keyPassword = keyPassword;
        }

        public Integer getDefaultAccessTokenTimeout() {
            return defaultAccessTokenTimeout;
        }

        public void setDefaultAccessTokenTimeout(Integer defaultAccessTokenTimeout) {
            this.defaultAccessTokenTimeout = defaultAccessTokenTimeout;
        }

        public Integer getDefaultRefreshTokenTimeout() {
            return defaultRefreshTokenTimeout;
        }

        public void setDefaultRefreshTokenTimeout(Integer defaultRefreshTokenTimeout) {
            this.defaultRefreshTokenTimeout = defaultRefreshTokenTimeout;
        }

        public Integer getFailedLoginAttemptAccountLockTimeout() {
            return failedLoginAttemptAccountLockTimeout;
        }

        public void setFailedLoginAttemptAccountLockTimeout(Integer failedLoginAttemptAccountLockTimeout) {
            this.failedLoginAttemptAccountLockTimeout = failedLoginAttemptAccountLockTimeout;
        }

        public Integer getMaxFailedLoginAttemptsForAccountLock() {
            return maxFailedLoginAttemptsForAccountLock;
        }

        public void setMaxFailedLoginAttemptsForAccountLock(Integer maxFailedLoginAttemptsForAccountLock) {
            this.maxFailedLoginAttemptsForAccountLock = maxFailedLoginAttemptsForAccountLock;
        }
    }

    public static class Endpoint {

        private String api;

        private Client client;

        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        public static class Client {
            private String save;

            public String getSave() {
                return save;
            }

            public void setSave(String save) {
                this.save = save;
            }
        }

    }
}
