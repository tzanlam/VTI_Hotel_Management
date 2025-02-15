package hotel.hotel_management.controller;

import hotel.hotel_management.modal.entity.hotel.Hotel;
import hotel.hotel_management.modal.request.HotelRequest;
import hotel.hotel_management.service.hotel.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/listHotel")
    @PreAuthorize("hasAnyAuthority('HOTELIER','RECEPTIONIST')")
    public ResponseEntity<?> listHotel(
            @RequestParam("accountId") int accountId
    ) {
        try{
            return ResponseEntity.ok(hotelService.findHotelByAccount(accountId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Hotel not found");
        }
    }

    @GetMapping("/hotelNumber")
    @PreAuthorize("hasAnyAuthority('HOTELIER','RECEPTIONIST')")
    public ResponseEntity<?> hotelNumber(@RequestParam("hotelId") int hotelId) {
        try{
            return ResponseEntity.ok(hotelService.findHotelById(hotelId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Hotel not found");
        }
    }

    @PostMapping("/newHotel")
    @PreAuthorize("hasAuthority('HOTELIER')")
    public ResponseEntity<?> addNewHotel(@RequestBody HotelRequest request) {
        try{
            return ResponseEntity.ok(hotelService.createHotel(request));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Add new hotel failed");
        }
    }

    @PutMapping("/updateHotel")
    @PreAuthorize("hasAuthority('HOTELIER')")
    public ResponseEntity<?> updateHotel(@RequestBody HotelRequest request, @RequestParam("hotelId") int hotelId) {
        try{
            return ResponseEntity.ok(hotelService.updateHotel(hotelId, request));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Update hotel failed");
        }
    }

    @PutMapping("/open_closeHotel")
    @PreAuthorize("hasAuthority('HOTELIER')")
    public ResponseEntity<?> changeStatus(@RequestParam("hotelId") int hotelId){
        try{
            return ResponseEntity.ok(hotelService.changeStatusHotel(hotelId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Change status hotel failed");
        }
    }
}
