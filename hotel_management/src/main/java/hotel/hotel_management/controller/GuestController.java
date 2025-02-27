package hotel.hotel_management.controller;

import hotel.hotel_management.modal.request.GuestRequest;
import hotel.hotel_management.service.guest.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/getGuests")
    public ResponseEntity<?> findAllByHotel(@RequestParam("hotelId") int hotelId) {
        try{
            return ResponseEntity.ok(guestService.getAllGuestsByHotelId(hotelId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getGuestById")
    public ResponseEntity<?> findGuestById(@RequestParam("guestId") int guestId) {
        try{
            return ResponseEntity.ok(guestService.getById(guestId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/postGuest")
    public ResponseEntity<?> postGuest(@RequestBody GuestRequest request) {
        try{
            return ResponseEntity.ok(guestService.createGuest(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/putGuest")
    public ResponseEntity<?> putGuest(@RequestBody GuestRequest request, @RequestParam("guestId") int guestId) {
        try{
            return ResponseEntity.ok(guestService.updateGuest(request, guestId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteGuest")
    public ResponseEntity<?> deleteGuest(@RequestParam("guestId") int guestId) {
        try{
            return ResponseEntity.ok(guestService.deleteGuest(guestId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/updateTypeGuest")
    public ResponseEntity<?> changeType(@RequestParam("guestId") int guestId, @RequestParam("type") int type) {
        try{
            return ResponseEntity.ok(guestService.changeStatus(guestId, type));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
