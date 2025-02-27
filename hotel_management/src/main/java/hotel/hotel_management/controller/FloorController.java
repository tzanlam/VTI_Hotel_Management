package hotel.hotel_management.controller;

import hotel.hotel_management.modal.request.FloorRequest;
import hotel.hotel_management.service.floor.FloorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping
public class FloorController {
    private final FloorService floorService;

    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }

    @GetMapping("/getFloors")
    public ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity<>(floorService.findAll(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Something went wrong when find floors", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getFloorByHotelId")
    public ResponseEntity<?> findByHotelId(@RequestParam("hotelId") int hotelId) {
        try{
            return ResponseEntity.ok(floorService.findByHotelId(hotelId));
        }catch (Exception e) {
            return new ResponseEntity<>("Something went wrong when find floor", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getFloorById")
    public ResponseEntity<?> findById(@RequestParam("id") int id) {
        try{
            return ResponseEntity.ok(floorService.findById(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong when find floor by id");
        }
    }

    @PostMapping("/postFloor")
    public ResponseEntity<?> createFloor(@RequestBody FloorRequest request){
        try{
            return ResponseEntity.ok(floorService.createFloor(request));
        }catch (Exception e) {
            return new ResponseEntity<>("Something went wrong when create floor", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/putFloor")
    public ResponseEntity<?> updateFloor(@RequestBody FloorRequest request, @RequestParam("id") int id){
        try{
            return ResponseEntity.ok(floorService.updateFloor(request,id));
        }catch (Exception e) {
            return new ResponseEntity<>("Something went wrong when update floor", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/hideOpenFloor")
    public ResponseEntity<?> hideOpenFloor(@RequestParam("id")int id){
        try{
            return ResponseEntity.ok(floorService.deleteFloor(id));
        }catch (Exception e) {
            return new ResponseEntity<>("Something went wrong when delete floor", HttpStatus.BAD_REQUEST);
        }
    }
}
