package com.saman.sso.config.orm;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

import static com.saman.sso.util.SecurityUtils.getCurrentUsername;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(getCurrentUsername());
    }
}