package hotel.hotel_management.controller;

import hotel.hotel_management.modal.request.ServiceRequest;
import hotel.hotel_management.service.service_hotel.ServiceInHotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ServiceController {
    private final ServiceInHotelService serviceInHotelService;

    public ServiceController(ServiceInHotelService serviceInHotelService) {
        this.serviceInHotelService = serviceInHotelService;
    }

    @GetMapping("/getServiceByHotel")
    @PreAuthorize("hasAnyAuthority('HOTELIER','MANAGER','RECEPTIONIST')")
    public ResponseEntity<?> findServiceByHotel(@RequestParam("hotelId") int hotelId) {
        try{
            return ResponseEntity.ok(serviceInHotelService.findAllServiceByHotelId(hotelId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/getServiceById")
    @PreAuthorize("hasAnyAuthority('HOTELIER','MANAGER','RECEPTIONIST')")
    public ResponseEntity<?> findServiceById(@RequestParam("serviceId") int serviceId) {
        try{
            return ResponseEntity.ok(serviceInHotelService.findServiceById(serviceId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/postService")
    @PreAuthorize("hasAnyAuthority('HOTELIER','MANAGER')")
    public ResponseEntity<?> createNew(@RequestBody ServiceRequest request){
        try{
            return ResponseEntity.ok(serviceInHotelService.createNewService(request));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/putService")
    @PreAuthorize("hasAnyAuthority('HOTELIER','MANAGER')")
    public ResponseEntity<?> updateService(@RequestBody ServiceRequest request, @RequestParam("serviceId") int serviceId){
        try{
            return ResponseEntity.ok(serviceInHotelService.updateService(request,serviceId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
