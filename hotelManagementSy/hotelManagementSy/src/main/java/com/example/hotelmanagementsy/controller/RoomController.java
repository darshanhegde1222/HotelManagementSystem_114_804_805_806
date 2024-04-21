package com.example.hotelmanagementsy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.hotelmanagementsy.model.Room;
import com.example.hotelmanagementsy.repository.RoomRepository;

@Controller
public class RoomController {

    @Autowired
    private RoomRepository roomRepository; // Assuming you have a RoomRepository interface


    @PostMapping("/add-room")
    public String addRoom(@RequestParam String roomId, @RequestParam String roomType) {
        Room room = new Room();
        room.setRoomId(roomId);
        room.setRoomType(roomType);
        roomRepository.save(room);
        return "redirect:/dashboard";
    }
}
