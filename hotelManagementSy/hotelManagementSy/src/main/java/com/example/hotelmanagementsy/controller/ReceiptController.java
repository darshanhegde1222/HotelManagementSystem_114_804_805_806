package com.example.hotelmanagementsy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.hotelmanagementsy.repository.ReservationRepository;
import com.example.hotelmanagementsy.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ReceiptController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/invoice")
    public ModelAndView generateInvoice(@RequestParam("reservationId") Long reservationId) {
        // Retrieve reservation details from the database
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);

        // Create a ModelAndView object and pass the reservation details to the Thymeleaf template
        ModelAndView modelAndView = new ModelAndView("invoice");
        modelAndView.addObject("reservation", reservation);

        return modelAndView;
    }
}