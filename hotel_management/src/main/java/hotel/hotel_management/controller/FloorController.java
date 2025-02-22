package hotel.hotel_management.controller;

import hotel.hotel_management.modal.request.FloorRequest;
import hotel.hotel_management.service.floor.FloorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class FloorController {
    private final FloorService floorService;

    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }

    @GetMapping("/findByHotel")
    @PreAuthorize("hasAnyAuthority('HOTELIER', 'MANAGER', 'RECEPTIONIST')")
    public ResponseEntity<?> findByHotel(@RequestParam("hotelId") int hotelId) {
        try {
            return ResponseEntity.ok(floorService.findByHotelId(hotelId));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Fail to find by hotel");
        }
    }

    @GetMapping("/findById")
    @PreAuthorize("hasAnyAuthority('HOTELIER', 'MANAGER', 'RECEPTIONIST')")
    public ResponseEntity<?> findById(@RequestParam("floorId") int floorId) {
        try{
            return ResponseEntity.ok(floorService.findById(floorId));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Fail to find by id");
        }
    }

    @PostMapping("/createFloor")
    @PreAuthorize("hasAnyAuthority('HOTELIER')")
    public ResponseEntity<?> createFloor(@RequestBody FloorRequest request){
        try{
            return ResponseEntity.ok(floorService.createNewFloor(request));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Fail to create new floor");
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('HOTELIER', 'MANAGER')")
    public ResponseEntity<?> updateFloor(
            @RequestBody FloorRequest request
            ,@RequestParam("floorId") int floorId)
    {
        try{
            return ResponseEntity.ok(floorService.updateFloor(request,floorId));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Fail to update floor");
        }
    }

    @PutMapping("/closeFloor")
    @PreAuthorize("hasAnyAuthority('HOTELIER', 'MANAGER')")
    public ResponseEntity<?> closeFloor(@RequestParam("floorId") int floorId){
        try{
            return ResponseEntity.ok(floorService.closeFloor(floorId));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Fail to close floor");
        }
    }
}
