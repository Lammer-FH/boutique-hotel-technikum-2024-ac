package org.technikum.backend.controller;

import org.technikum.backend.dto.RoomDTO;
import org.technikum.backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<RoomDTO> getAllRooms(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(required = false) Date startDate,
                                     @RequestParam(required = false) Date endDate) {
        if (startDate != null && endDate != null) {
            return roomService.getAvailableRooms(page, size, startDate, endDate);
        } else {
            return roomService.getAllRooms(page, size);
        }
    }

    @PostMapping
    public RoomDTO createRoom(@RequestBody RoomDTO roomDTO) {
        return roomService.createRoom(roomDTO);
    }
}