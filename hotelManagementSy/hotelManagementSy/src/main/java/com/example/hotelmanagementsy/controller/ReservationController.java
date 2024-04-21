// ReservationController.java
package com.example.hotelmanagementsy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.hotelmanagementsy.repository.ReservationRepository;
import com.example.hotelmanagementsy.model.Reservation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

@Controller
public class ReservationController {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/reservation")
    public String showReservationPage() {
        return "reservation";
    }

    @PostMapping("/reservation")
    public String makeReservation(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkin,
            @RequestParam("checkout") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkout,
            @RequestParam("roomType") String roomType,
            @RequestParam("numberOfRooms") int numberOfRooms,
            @RequestParam("vipBenefits") String vipBenefits, // Assuming you get VIP status as a string
            RedirectAttributes redirectAttributes,
            Model model) {

        // Calculate the price based on the room type
        Map<String, Double> roomTypePrices = new HashMap<>();
        roomTypePrices.put("single", 500.0);
        roomTypePrices.put("double", 1000.0);
        roomTypePrices.put("suite", 1500.0);

        // Get the price based on the selected room type
        double price = roomTypePrices.getOrDefault(roomType, 0.0);

        // Calculate the total price by multiplying the price per room with the number of rooms
        double totalPrice = price * numberOfRooms;

        // Set VIP status based on the input
        boolean isVip = vipBenefits.equalsIgnoreCase("yes");

        // Create a Reservation object
        Reservation reservation = new Reservation();
        reservation.setName(name);
        reservation.setEmail(email);
        reservation.setPhone(phone);
        reservation.setCheckin(checkin);
        reservation.setCheckout(checkout);
        reservation.setRoomType(roomType);
        reservation.setNumberOfRooms(numberOfRooms);
        reservation.setIsVip(isVip);
        reservation.setPricePerRoom(price);
        reservation.setTotalPrice(totalPrice);
        reservation.setStatus("pending...");

        // Save the reservation to the database
        reservationRepository.save(reservation);

        // Add a flash attribute to display success message
        redirectAttributes.addFlashAttribute("successMessage", "Reservation successful");

        return "redirect:/reservation";
    }


    @PostMapping("/confirm-reservation")
    public String confirmReservation(
            @RequestParam("reservationId") Long reservationId,
            RedirectAttributes redirectAttributes) {

        // Find the reservation by ID
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);

        // Check if the reservation exists
        if (reservation != null) {
            // Update the status to "Confirmed"
            reservation.setStatus("Confirmed");
            // Save the updated reservation
            reservationRepository.save(reservation);
            // Add a flash attribute to display success message
            redirectAttributes.addFlashAttribute("successMessage", "Reservation confirmed");
        } else {
            // Add a flash attribute to display error message
            redirectAttributes.addFlashAttribute("errorMessage", "Reservation not found");
        }

        return "redirect:/dashboard";
        // Redirect to the dashboard page
    }


    @PostMapping("/reject-reservation")
    public String rejectReservation(
            @RequestParam("reservationId") Long reservationId,
            RedirectAttributes redirectAttributes) {

        // Find the reservation by ID
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);

        // Check if the reservation exists
        if (reservation != null) {
            // Update the status to "Rejected"
            reservation.setStatus("Rejected");
            // Save the updated reservation
            reservationRepository.save(reservation);
            // Add a flash attribute to display success message
            redirectAttributes.addFlashAttribute("successMessage", "Reservation rejected");
        } else {
            // Add a flash attribute to display error message
            redirectAttributes.addFlashAttribute("errorMessage", "Reservation not found");
        }

        return "redirect:/dashboard";
        // Redirect to the reservation page
    }


}