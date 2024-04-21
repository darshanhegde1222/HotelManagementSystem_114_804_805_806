package com.example.hotelmanagementsy.service;

import com.example.hotelmanagementsy.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotelmanagementsy.repository.ReservationRepository;



import java.util.List;

@Service
public class ReservationManagementService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationManagementService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;

    }

    // Dummy list to simulate booked rooms
    public List<Reservation> getAllBookedRooms() {
        return reservationRepository.findAll(); // Assuming ReservationRepository has a findAll method
    }

    // Other methods for reservation management
}
