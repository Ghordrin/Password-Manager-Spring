package com.yannickdriessens.passwordmanager.Repository;

import com.yannickdriessens.passwordmanager.model.User;
import com.yannickdriessens.passwordmanager.model.Vault;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VaultRepository extends CrudRepository<Vault, Long> {
    Optional<Vault> findByUser(User user);
}
