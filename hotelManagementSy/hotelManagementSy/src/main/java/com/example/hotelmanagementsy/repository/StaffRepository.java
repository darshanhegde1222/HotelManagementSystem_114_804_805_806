package com.example.hotelmanagementsy.repository;

import com.example.hotelmanagementsy.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    // Define custom queries if needed
    Staff findByStaffId(String staffId);
}
