package com.yannickdriessens.passwordmanager.repository;

import com.yannickdriessens.passwordmanager.model.HashedPassword;
import org.springframework.data.repository.CrudRepository;

public interface PasswordRepository extends CrudRepository<HashedPassword, Long> {
}
