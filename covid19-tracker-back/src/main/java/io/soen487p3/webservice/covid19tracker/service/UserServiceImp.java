package io.soen487p3.webservice.covid19tracker.service;


import io.soen487p3.webservice.covid19tracker.model.Role;
import io.soen487p3.webservice.covid19tracker.model.User;
import io.soen487p3.webservice.covid19tracker.repository.RoleRepository;
import io.soen487p3.webservice.covid19tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class UserServiceImp implements UserService{



    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName).get();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsEnabled(true);
        user.setIsVerified(false);
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
        return;
    }

    @Override
    public boolean isUserAlreadyPresent(User user) {
        String userName = user.getUserName();
        String email = user.getEmail();

        try {
            userRepository.findByEmail(email).get();
            return true;
        } catch (NoSuchElementException exception) {
            // Output expected NoSuchElementExceptions.
            System.out.println(exception.getMessage());
        }

        try {
            userRepository.findByUserName(userName).get();
            return true;
        } catch (NoSuchElementException exception) {
            // Output expected NoSuchElementExceptions.
            System.out.println(exception.getMessage());
        }

        return false;


    }

}