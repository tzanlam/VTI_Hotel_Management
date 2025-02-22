package hotel.hotel_management.controller;

import hotel.hotel_management.modal.request.RoomRequest;
import hotel.hotel_management.service.room.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/getRoomById")
    public ResponseEntity<?> findRoomById(@RequestParam("roomId") int id) {
        try{
            return ResponseEntity.ok(roomService.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error when find room by id.");
        }
    }

    @PostMapping("/setRoom")
    public ResponseEntity<?> setRoom(@RequestBody RoomRequest request) {
        try{
            return ResponseEntity.ok(roomService.createRoom(request));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error when set room.");
        }
    }
}
