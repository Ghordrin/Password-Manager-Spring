package com.yannickdriessens.passwordmanager.Controller;

import com.yannickdriessens.passwordmanager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class Login {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/home")
    public String Home(HttpSession session, Principal principal)
    {
        session.setAttribute("user", userRepository.findByUsername(principal.getName()).get());
        return "home";
    }

    @GetMapping("/")
    public String Homepage(){
        return "homepage";
    }

    @GetMapping("/login")
    public String login(){
        return "login/Login";
    }
}
