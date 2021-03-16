package com.yannickdriessens.passwordmanager.controller;

import com.yannickdriessens.passwordmanager.repository.PasswordRepository;
import com.yannickdriessens.passwordmanager.repository.UserRepository;
import com.yannickdriessens.passwordmanager.repository.VaultRepository;
import com.yannickdriessens.passwordmanager.model.HashedPassword;
import com.yannickdriessens.passwordmanager.model.User;
import com.yannickdriessens.passwordmanager.model.Vault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;


@Controller
public class VaultController {

    @Autowired
    VaultRepository vaultRepository;

    @Autowired
    PasswordRepository passwordRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/myvault")
    public String GoToVault(HttpSession session, Principal principal){
        session.setAttribute("user", userRepository.findByUsername(principal.getName()).get());
        User user = (User) session.getAttribute("user");
        vaultRepository.findByUser(user);
        return "vault";
    }

    @PostMapping("/myvault/add")
    public String addPassword(HashedPassword hashedPassword, HttpSession session){
        User user = (User) session.getAttribute("user");
        Vault vault = vaultRepository.findByUser(user).get();
        hashedPassword.setVault(vault);
        passwordRepository.save(hashedPassword);
        vault.addPasswordToSet(hashedPassword);
        vaultRepository.save(vault);
        return "redirect:/myvault/";
    }

    @GetMapping("/myvault/get")
    public String getPasswordsFromVault(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        Vault vault = vaultRepository.findByUser(user).get();
        model.addAttribute("passwordList", vault.getHashedPasswords());
        return "redirect:/myvault";

    }

    @ModelAttribute(value = "newPassword")
    public HashedPassword newPassword(){
        return new HashedPassword();
    }

    @ModelAttribute
    public void getPassWordList(Model model, HttpSession session, Principal principal){
        session.setAttribute("user", userRepository.findByUsername(principal.getName()).get());
        User user = (User) session.getAttribute("user");
        Vault vault = vaultRepository.findByUser(user).get();
        model.addAttribute("passwordSet", vault.getHashedPasswords() );
    }




}
