package com.example.hotelmanagementsy.controller;

import com.example.hotelmanagementsy.service.ReservationManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.hotelmanagementsy.repository.ReservationRepository;
import com.example.hotelmanagementsy.model.Reservation;
import com.example.hotelmanagementsy.service.*;
import com.example.hotelmanagementsy.repository.RoomRepository;

import java.util.List;

@Controller
public class DashboardController {

    private final ReservationManagementService reservationManagementService;
    private final ReservationDataService reservationDataService;
    private final RoomRepository roomRepository;

    @Autowired
    public DashboardController(ReservationManagementService reservationManagementService, ReservationDataService reservationDataService, RoomRepository roomRepository) {
        this.reservationManagementService = reservationManagementService;
        this.reservationDataService = reservationDataService;
        this.roomRepository = roomRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Retrieve booked rooms data from the reservation table using ReservationManagementService
        List<Reservation> bookedRooms = reservationManagementService.getAllBookedRooms();

        // Add booked rooms data to the model attribute
        model.addAttribute("bookedRooms", bookedRooms);

        // Calculate the total number of booked rooms based on the number of confirmations
        long totalBookedRooms = bookedRooms.stream().filter(room -> room.getStatus().equals("Confirmed")).count();
        model.addAttribute("totalBookedRooms", totalBookedRooms);

        // Fetch the total number of rooms from the room repository
        long totalRooms = roomRepository.count();
        model.addAttribute("totalRooms", totalRooms);

        // Calculate the total number of particular type of rooms (e.g., Single rooms)
        long singleRooms = roomRepository.countByRoomType("Single");
        model.addAttribute("singleRooms", singleRooms);

        long doubleRooms = roomRepository.countByRoomType("Double");
        model.addAttribute("doubleRooms", doubleRooms);

        long suiteRooms = roomRepository.countByRoomType("Suite");
        model.addAttribute("suiteRooms", suiteRooms);

        return "dashboard"; // Return the dashboard HTML page
    }

    @PostMapping("/cancel-booking")
    public String cancelBooking(@RequestParam("reservationId") Long reservationId, RedirectAttributes redirectAttributes) {
        // Logic to cancel the booking using ReservationDataService
        reservationDataService.cancelReservation(reservationId);
        // Optionally, you can add a message to confirm cancellation
        redirectAttributes.addFlashAttribute("message", "Booking canceled successfully");
        // Redirect back to the dashboard or any other page
        return "redirect:/dashboard";
    }
}
