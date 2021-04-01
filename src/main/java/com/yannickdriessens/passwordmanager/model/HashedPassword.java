package com.yannickdriessens.passwordmanager.model;


import com.yannickdriessens.passwordmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
    @Size(min = 5, message = "Password must be minimum 5 characters!")
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

    public String getPassword() throws Exception {
        return password;
    }

    public void setPassword(String password) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        this.password = password;
    }

}
