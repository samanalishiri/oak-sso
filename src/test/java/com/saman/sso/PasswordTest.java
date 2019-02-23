package com.saman.sso;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void init() {
        Assert.assertNotNull(passwordEncoder);
    }

    @Test
    public void generatePassword() {
        String password = passwordEncoder.encode("admin");
        System.out.println("password = " + password);
    }
}
