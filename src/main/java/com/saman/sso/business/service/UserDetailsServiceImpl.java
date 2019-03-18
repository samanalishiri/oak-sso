package com.saman.sso.business.service;

import com.saman.sso.business.model.UserModel;
import com.saman.sso.business.repository.UserRepository;
import com.saman.sso.business.transform.UserTransformer;
import com.saman.sso.domain.UserEntity;
import com.saman.sso.domain.refdata.ReadOnlyRefData;
import com.saman.sso.domain.refdata.RefData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Saman Alishiri, samanalishiri@gmail.com
 */
@Service(UserDetailsServiceImpl.BEAN_NAME)
public class UserDetailsServiceImpl extends AbstractService<Long, UserEntity, UserModel, UserRepository> implements UserDetailsService, UserService {

    public static final String BEAN_NAME = "userDetailsService";

    @Autowired
    @Qualifier(value = "userPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository, UserTransformer transformer) {
        super(repository, transformer);
    }

    @Override
    public UserEntity loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findByUsername(s);
    }

    public void beforeSave(UserModel model) {
        String encodedPassword = passwordEncoder.encode(model.getPassword());
        model.setPassword(encodedPassword);
    }

    @Override
    public Optional<Collection<ReadOnlyRefData<Integer>>> findAllRefData(Class<? extends RefData> c) {
        return Optional.empty();
    }
}
