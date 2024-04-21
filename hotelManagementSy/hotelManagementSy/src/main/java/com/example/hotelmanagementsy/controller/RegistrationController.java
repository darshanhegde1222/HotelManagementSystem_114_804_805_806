
package com.example.hotelmanagementsy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.hotelmanagementsy.repository.UserRepository;
import com.example.hotelmanagementsy.model.Users;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // This will resolve to register.html
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("number") String number,
                               RedirectAttributes redirectAttributes) {
        // Check if the username already exists
        if (userRepository.existsByUsername(username)) {
            // Add an error flash attribute to display error message
            redirectAttributes.addFlashAttribute("errorMessage", "Username already taken. Please choose a different username.");
            return "redirect:/register"; // Redirect back to the registration form
        }

        // Create a new User object
        Users newUser = new Users();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setNumber(number);

        // Save the user to the database
        userRepository.save(newUser);

        // Add a flash attribute to display success message
        redirectAttributes.addFlashAttribute("successMessage", "Successfully registered. You can now login.");

        return "redirect:/login"; // Redirect to the login page after successful registration
    }

}