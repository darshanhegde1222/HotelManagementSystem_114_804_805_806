package com.example.hotelmanagementsy.controller;

import com.example.hotelmanagementsy.model.Users;
import com.example.hotelmanagementsy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {



    @Autowired
    private UserRepository userRepository;

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes) {

        Users  user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            redirectAttributes.addFlashAttribute("success", "Successfully logged in!");
            return "redirect:/customer";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        }
    }
}
