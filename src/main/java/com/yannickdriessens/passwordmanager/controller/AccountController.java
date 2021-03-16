package com.yannickdriessens.passwordmanager.controller;


import com.yannickdriessens.passwordmanager.model.User;
import com.yannickdriessens.passwordmanager.repository.UserRepository;
import com.yannickdriessens.passwordmanager.repository.VaultRepository;
import com.yannickdriessens.passwordmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    VaultRepository vaultRepository;

    @Autowired
    UserService userService;


    @GetMapping("/user/details")
    public String getuserDetails(Authentication authentication, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        Long vaultId = vaultRepository.findByUser(user).get().getVaultId();
        Long userId = user.getId();
        model.addAttribute("vaultId", vaultId);
        model.addAttribute("userId", userId);
        model.addAttribute("userObj", authentication.getPrincipal());
        return "account/account_overview";
    }


}
