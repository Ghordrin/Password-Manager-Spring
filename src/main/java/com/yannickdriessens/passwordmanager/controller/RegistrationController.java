package com.yannickdriessens.passwordmanager.controller;

import com.yannickdriessens.passwordmanager.repository.UserRepository;
import com.yannickdriessens.passwordmanager.repository.VaultRepository;
import com.yannickdriessens.passwordmanager.exceptions.UsernameAlreadyExistsException;
import com.yannickdriessens.passwordmanager.model.User;
import com.yannickdriessens.passwordmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VaultRepository vaultRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String Register(Model model) {
        model.addAttribute("user", new User());
        return "register/registration";
    }

    @PostMapping("/signup")
    public String signup(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register/registration";
        } else {
            try {
                userService.registerNewUser(user);
            } catch (UsernameAlreadyExistsException existsException) {
                model.addAttribute("error", "A user with that name already exists!");
                System.out.println("User already exists");
                return "register/registration";
            }
            return "register/register_ok";
        }

    }

}
