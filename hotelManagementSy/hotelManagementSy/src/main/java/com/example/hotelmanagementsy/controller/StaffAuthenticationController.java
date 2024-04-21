package com.example.hotelmanagementsy.controller;

import com.example.hotelmanagementsy.model.Staff;
import com.example.hotelmanagementsy.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StaffAuthenticationController {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffAuthenticationController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @PostMapping("/staff-authenticate")
    public String authenticate(@RequestParam("staffId") String staffId,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes) {
        // Find staff by staff ID
        Staff staff = staffRepository.findByStaffId(staffId);

        // Check if staff exists and password matches
        if (staff != null && staff.getPassword().equals(password)) {
            redirectAttributes.addFlashAttribute("success", "Successfully logged in as staff!");
            return "redirect:/dashboard"; // Redirect to the staff dashboard page upon successful login
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid staff ID or password");
            return "redirect:/staff-login"; // Redirect back to the main login page with an error message
        }
    }
}
