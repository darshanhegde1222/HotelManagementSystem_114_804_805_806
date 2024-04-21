package com.example.hotelmanagementsy.controller;

import com.example.hotelmanagementsy.model.Users;
import com.example.hotelmanagementsy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/customer")
    public String showUserDetails(HttpServletRequest request, Model model) {
        // Assuming you have stored the username in the session attribute "username"
        String username = (String) request.getSession().getAttribute("username");

        // Find the user by username
        Users user = userRepository.findByUsername(username);

        // Add user details to the model
        if (user != null) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("phone", user.getNumber());
        }

        // Return the view name
        return "customer";
    }
}
