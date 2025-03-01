package hotel.hotel_management.controller;

import hotel.hotel_management.service.bookedHistory.BookedHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class BookedHistoryController {
    private final BookedHistoryService bookedHistoryService;

    public BookedHistoryController(BookedHistoryService bookedHistoryService) {
        this.bookedHistoryService = bookedHistoryService;
    }

    @GetMapping("/getHistories")
    public ResponseEntity<?> findHistories() {
        try{
            return new ResponseEntity<>(bookedHistoryService.findHistories(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getHistoryByHotel")
    public ResponseEntity<?> findHistoriesByHotel(@RequestParam("hotelId")int hotelId) {
        try{
            return ResponseEntity.ok(bookedHistoryService.findByHotelId(hotelId));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getHistoryById")
    public ResponseEntity<?> findHistoriesById(@RequestParam("id")int id) {
        try{
            return ResponseEntity.ok(bookedHistoryService.findBookedHistoryById(id));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/postHistory")
    public ResponseEntity<?> postHistory(@RequestParam("bookedId") int bookedId, @RequestParam("accountId") int accountId) {
        try{
            return ResponseEntity.ok(bookedHistoryService.actionByEmployee(bookedId, accountId));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
