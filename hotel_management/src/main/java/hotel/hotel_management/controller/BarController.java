package hotel.hotel_management.controller;

import hotel.hotel_management.modal.request.BarRequest;
import hotel.hotel_management.service.bar.BarService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class BarController {
    private final BarService barService;

    public BarController(BarService barService) {
        this.barService = barService;
    }

    @GetMapping("/getBars")
    @PreAuthorize("hasAnyAuthority('HOTELIER','MANAGER')")
    public ResponseEntity<?> findAllBars() {
        try{
            return ResponseEntity.ok(barService.getBars());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/setBar")
    @PreAuthorize("hasAnyAuthority('HOTELIER','MANAGER')")
    public ResponseEntity<?> setBar(@RequestBody BarRequest request) {
        try{
            return ResponseEntity.ok(barService.addBar(request));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/putBar")
    @PreAuthorize("hasAnyAuthority('HOTELIER', 'MANAGER')")
    public ResponseEntity<?> putBar(@RequestBody BarRequest request, @RequestParam("barId") int barId) {
        try{
            return ResponseEntity.ok(barService.updateBar(request,barId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/hide")
    @PreAuthorize("hasAnyAuthority('HOTELIER', 'MANAGER')")
    public ResponseEntity<?> hideBar(@RequestParam("barId") int barId) {
        try{
            return ResponseEntity.ok(barService.hideOpenBar(barId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
}