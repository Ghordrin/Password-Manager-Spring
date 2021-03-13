package com.yannickdriessens.passwordmanager.service;

import com.yannickdriessens.passwordmanager.Repository.UserRepository;
import com.yannickdriessens.passwordmanager.model.User;
import com.yannickdriessens.passwordmanager.security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String s){
        Optional<User> user = userRepository.findByUsername(s);
        System.out.println("Found username: " + s);
        user.orElseThrow(() -> new UsernameNotFoundException("No user found by name: " + s));
        return user.map(CustomUserDetail::new).get();
    }
}
