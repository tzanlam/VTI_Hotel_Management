package hotel.hotel_management.controller;

import hotel.hotel_management.modal.request.BookedRequest;
import hotel.hotel_management.service.booked.BookedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class BookedController {
    private final BookedService bookedService;

    public BookedController(BookedService bookedService) {
        this.bookedService = bookedService;
    }

    @GetMapping("/getBookedByHotel")
    public ResponseEntity<?> findBookedByHotel(@RequestParam("hotelId") int hotelId) {
        try{
            return ResponseEntity.ok(bookedService.getBookedByHotel(hotelId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getBookedById")
    public ResponseEntity<?> findBookedById(@RequestParam("id") int id) {
        try{
            return ResponseEntity.ok(bookedService.getById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getByRequest")
    public ResponseEntity<?> findBookedByRequest(@RequestParam("request") String request, @RequestParam("field") String ...fields) {
        try{
            return ResponseEntity.ok(bookedService.searchBySpec(request,fields));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/postBooked")
    public ResponseEntity<?> postBooked(@RequestBody BookedRequest request, @RequestParam("isBooked") boolean isBooked) {
        try{
            return ResponseEntity.ok(bookedService.createBooked(request,isBooked));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/putBooked")
    public ResponseEntity<?> putBooked(@RequestBody BookedRequest request, @RequestParam("bookedId") int bookedId) {
        try{
            return ResponseEntity.ok(bookedService.updateBooked(bookedId,request));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/cancelBooked")
    public ResponseEntity<?> cancelBooked(@RequestParam("bookedId") int bookedId) {
        try{
            return ResponseEntity.ok(bookedService.deleteBooked(bookedId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}