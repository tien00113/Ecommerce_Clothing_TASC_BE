package com.tasc.clothing.initializer;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tasc.clothing.model.User;
import com.tasc.clothing.repository.UserRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByUsernameOrEmail("admin", "admin") == null) {
            User admin = new User();
            admin.setUsername("admin");

            admin.setPassword(passwordEncoder.encode("12345678"));

            admin.setFirstName("admin");

            admin.setLastName("promax");

            admin.setEmail("admin@gmail.com");

            admin.setRoles(Collections.singleton(User.Role.ADMIN));

            userRepository.save(admin);
        }

    }

}
