package com.yannickdriessens.passwordmanager.model;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class HashedPassword implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long hashedPasswordId;
    @ManyToOne(cascade = CascadeType.ALL)
    private Vault vault;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 20, message = "Website identifier must be between 3 - 20 characters")
    private String websiteIdentifier;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 20, message = "Password must be between 3 - 20 characters")
    private String password;



    public HashedPassword() {
    }

    public long getHashedPasswordId() {
        return hashedPasswordId;
    }

    public void setHashedPasswordId(long hashedPasswordId) {
        this.hashedPasswordId = hashedPasswordId;
    }

    public Vault getVault() {
        return vault;
    }

    public void setVault(Vault vault) {
        this.vault = vault;
    }

    public String getWebsiteIdentifier() {
        return websiteIdentifier;
    }

    public void setWebsiteIdentifier(String websiteIdentifier) {
        this.websiteIdentifier = websiteIdentifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
