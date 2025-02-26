package hotel.hotel_management.controller;

import hotel.hotel_management.modal.request.HotelRequest;
import hotel.hotel_management.service.hotel.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/getHotels")
    public ResponseEntity<?> getHotels() {
        try{
            return ResponseEntity.ok(hotelService.getHotels());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Something went wrong when try to get hotels");
        }
    }

    @GetMapping("/getAccountByHotelId")
    public ResponseEntity<?> getAccountByHotelId(@RequestParam("hotelId") int hotelId) {
        try{
            return ResponseEntity.ok(hotelService.getByHotelId(hotelId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Something went wrong when try to get hotel by hotelId");
        }
    }

    @GetMapping("/getHotelById")
    public ResponseEntity<?> getHotelById(@RequestParam("hotelId") int hotelId) {
        try{
            return ResponseEntity.ok(hotelService.getHotelById(hotelId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Something went wrong when try to get hotel by hotelId");
        }
    }

    @PostMapping("/postHotel")
    public ResponseEntity<?> putHotel(@RequestBody HotelRequest request) {
        try{
            return ResponseEntity.ok(hotelService.createHotel(request));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Something went wrong when try to create hotel");
        }
    }

    @PutMapping("/putHotel")
    public ResponseEntity<?> updateHotel(@RequestBody HotelRequest request, @RequestParam("hotelId") int hotelId) {
        try{
            return ResponseEntity.ok(hotelService.updateHotel(request, hotelId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Something went wrong when try to update hotel");
        }
    }

    @PutMapping
    public ResponseEntity<?> deleteHotel(@RequestParam("hotelId") int hotelId) {
        try{
            return ResponseEntity.ok(hotelService.deleteHotel(hotelId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Something went wrong when try to delete hotel");
        }
    }
}
