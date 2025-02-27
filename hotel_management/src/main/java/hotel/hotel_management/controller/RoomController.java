package hotel.hotel_management.controller;

import hotel.hotel_management.modal.request.RoomRequest;
import hotel.hotel_management.service.room.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/getRooms")
    public ResponseEntity<?> getRooms() {
        try{
            return ResponseEntity.ok(roomService.getAllRoom());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error getting rooms");
        }
    }

    @GetMapping("/getRoomByFloorId")
    public ResponseEntity<?> getRoomByFloorId(@RequestParam("floorId") int floorId) {
        try{
            return ResponseEntity.ok(roomService.getByFloorId(floorId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error getting room by floorId");
        }
    }

    @GetMapping("/getRoomByRoomId")
    public ResponseEntity<?> getRoomByRoomId(@RequestParam("roomId") int roomId) {
        try{
            return ResponseEntity.ok(roomService.getByRoomId(roomId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error getting room by roomId");
        }
    }

    @PostMapping("/postRoom")
    public ResponseEntity<?> setRoom(@RequestBody RoomRequest request) {
        try{
            return ResponseEntity.ok(roomService.createRoom(request));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error creating room");
        }
    }

    @PutMapping("/putRoom")
    public ResponseEntity<?> putRoom(@RequestBody RoomRequest request, @RequestParam("roomId") int roomId) {
        try{
            return ResponseEntity.ok(roomService.updateRoom(request,roomId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error updating room");
        }
    }

    @PutMapping("/changeStatus")
    public ResponseEntity<?> changeStatus(@RequestParam("roomId") int roomId, @RequestParam("status") int status) {
        try{
            return ResponseEntity.ok(roomService.changeStatus(roomId,status));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error changing status");
        }
    }

    @PutMapping("/closeOpenRoom")
    public ResponseEntity<?> closeOpenRoom(@RequestParam("roomId") int roomId) {
        try{
            return ResponseEntity.ok(roomService.deleteRoom(roomId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error closing room");
        }
    }
}
