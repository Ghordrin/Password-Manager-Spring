package com.yannickdriessens.passwordmanager.Repository;

import com.yannickdriessens.passwordmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>findByUsername(String name);
}
