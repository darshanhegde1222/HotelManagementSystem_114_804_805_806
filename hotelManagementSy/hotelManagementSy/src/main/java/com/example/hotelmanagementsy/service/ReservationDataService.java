package com.example.hotelmanagementsy.service;

import com.example.hotelmanagementsy.model.Reservation;
import com.example.hotelmanagementsy.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationDataService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationDataService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public void cancelReservation(Long reservationId) {
        // Check if the reservation exists
        if (reservationRepository.existsById(reservationId)) {
            // If the reservation exists, delete it
            reservationRepository.deleteById(reservationId);
        } else {
            // Handle the case where the reservation does not exist
            throw new IllegalArgumentException("Reservation with ID " + reservationId + " does not exist.");
        }
    }
}
