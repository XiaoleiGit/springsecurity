package org.example.springsecurity.service;

import org.example.springsecurity.model.MyUserDetails;
import org.example.springsecurity.model.User;
import org.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> users = userRepository.findByUserName((userName));

        users.orElseThrow(()->new UsernameNotFoundException("User not found: " + userName));

        return users.map(MyUserDetails::new).get();
    }
}
