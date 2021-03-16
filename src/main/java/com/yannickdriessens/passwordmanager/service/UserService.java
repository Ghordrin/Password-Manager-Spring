package com.yannickdriessens.passwordmanager.service;

import com.yannickdriessens.passwordmanager.repository.UserRepository;
import com.yannickdriessens.passwordmanager.repository.VaultRepository;
import com.yannickdriessens.passwordmanager.exceptions.UsernameAlreadyExistsException;
import com.yannickdriessens.passwordmanager.model.User;
import com.yannickdriessens.passwordmanager.model.Vault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VaultRepository vaultRepository;

    @Transactional
    public User registerNewUser(User user) throws UsernameAlreadyExistsException {
        if(userNameExists(user.getUsername())){
            throw new UsernameAlreadyExistsException("Username already exists in database: " + user.getUsername());
        }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setEnabled(1);
            user.setRole("ROLE_USER");
            user.setPassword(encodedPassword);
            Vault vault = new Vault();
            vault.setUser(user);
            user.setVault(vault);
            userRepository.save(user);
            vaultRepository.save(vault);
            return user;
    }





    private boolean userNameExists(String username){
        return userRepository.findByUsername(username).isPresent();
    }


}
