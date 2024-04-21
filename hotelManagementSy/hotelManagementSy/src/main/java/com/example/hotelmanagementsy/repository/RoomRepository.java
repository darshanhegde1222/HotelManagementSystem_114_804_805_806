package com.example.hotelmanagementsy.repository;

import com.example.hotelmanagementsy.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    // Custom query method to count rooms by room type
    long countByRoomType(String roomType);

}
