package com.example.hotelmanagementsy.repository;

import com.example.hotelmanagementsy.model.Reservation;
import com.example.hotelmanagementsy.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Define a custom query method to find reservations by user
}
