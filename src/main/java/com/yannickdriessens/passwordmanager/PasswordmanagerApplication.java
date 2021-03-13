package com.yannickdriessens.passwordmanager;

import com.yannickdriessens.passwordmanager.Repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class PasswordmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasswordmanagerApplication.class, args);
    }

}
