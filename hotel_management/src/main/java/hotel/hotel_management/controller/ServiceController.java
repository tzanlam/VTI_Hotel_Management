package hotel.hotel_management.controller;

import hotel.hotel_management.modal.request.ServiceRequest;
import hotel.hotel_management.service.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/findServiceByHotel")
    public ResponseEntity<?> findServiceByHotel(@RequestParam("hotelId") int hotelId) {
        try{
            return ResponseEntity.ok(serviceService.findByHotelId(hotelId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Something went wrong when finding service by hotel");
        }
    }

    @PostMapping("/postService")
    public ResponseEntity<?> postService(@RequestBody ServiceRequest serviceRequest) {
        try{
            return ResponseEntity.ok(serviceService.createNewService(serviceRequest));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong when posting service");
        }
    }

    @PutMapping("/putService")
    public ResponseEntity<?> putService(@RequestBody ServiceRequest serviceRequest, @RequestParam("serviceId") int serviceId) {
        try{
            return ResponseEntity.ok(serviceService.updateService(serviceRequest, serviceId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Something went wrong when updating service");
        }
    }

    @PutMapping("/setCloseOpen")
    public ResponseEntity<?> setCloseOpen(@RequestParam("serviceId") int serviceId) {
        try{
            return ResponseEntity.ok(serviceService.deleteService(serviceId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Something went wrong when deleting service");
        }
    }
}
