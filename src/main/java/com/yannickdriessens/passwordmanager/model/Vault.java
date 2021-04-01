package com.yannickdriessens.passwordmanager.model;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Vault implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long vaultId;
    @OneToOne(cascade = CascadeType.ALL)
    User user;

    @OneToMany  (cascade = CascadeType.ALL)
    private Set<HashedPassword> hashedPasswords;

    public Vault() {
    }


    public long getVaultId() {
        return vaultId;
    }

    public void setVaultId(long vaultId) {
        this.vaultId = vaultId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<HashedPassword> getHashedPasswords() {
        return hashedPasswords;
    }

    public void setHashedPasswords(Set<HashedPassword> hashedPasswords) {
        this.hashedPasswords = hashedPasswords;
    }

    public void addPasswordToSet(HashedPassword hashedPassword){
        this.hashedPasswords.add(hashedPassword);
    }

}
