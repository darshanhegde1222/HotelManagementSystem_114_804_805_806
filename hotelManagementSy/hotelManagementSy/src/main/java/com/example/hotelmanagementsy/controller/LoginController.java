package com.example.hotelmanagementsy.controller;

import com.example.hotelmanagementsy.model.Users;
import com.example.hotelmanagementsy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This will resolve to login.html or login.jsp based on your configuration
    }

    @GetMapping("/staff-login")
    public String showStaffLoginPage() {
        return "staff-login"; // This will resolve to staff-login.html or staff-login.jsp based on your configuration
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        // Retrieve username from the login form
        String username = request.getParameter("username");

        // Find the user by username
        Users user = userRepository.findByUsername(username);
        if (user != null) {
            // Get the HttpSession object
            HttpSession session = request.getSession();

            // Set the username in the session attribute
            session.setAttribute("username", username);
            return "redirect:/customer"; // Redirect to the customer page
        } else {
            // Handle the case when user is not found
            model.addAttribute("error", "User not found");
            return "login"; // Return to the login page with an error message
        }
    }
}
