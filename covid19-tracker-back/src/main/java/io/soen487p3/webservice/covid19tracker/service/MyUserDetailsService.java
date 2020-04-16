package io.soen487p3.webservice.covid19tracker.service;

import io.soen487p3.webservice.covid19tracker.model.MyUserDetails;
import io.soen487p3.webservice.covid19tracker.model.User;
import io.soen487p3.webservice.covid19tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Username %s could not be found" + userName));

        return user.map(MyUserDetails::new).get();
    }

    public UserDetails findUserByEmail(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(email);

        user.orElseThrow(() -> new UsernameNotFoundException(String.format("Email %s could not be found", email)));

        return user.map(MyUserDetails::new).get();
    }
}
