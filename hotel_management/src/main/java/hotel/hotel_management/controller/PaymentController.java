package hotel.hotel_management.controller;

import hotel.hotel_management.modal.request.PaymentRequest;
import hotel.hotel_management.service.Payment.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/getPaymentByHotelId")
    public ResponseEntity<?> findPaymentByHotelId(@RequestParam("hotelId") int hotelId) {
        try{
            return ResponseEntity.ok(paymentService.findAllByHotelId(hotelId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getPaymentByAccountId")
    public ResponseEntity<?> findPaymentByAccountId(@RequestParam("accountId") int accountId) {
        try{
            return ResponseEntity.ok(paymentService.findPaymentByAccountId(accountId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getPaymentById")
    public ResponseEntity<?> findPaymentById(@RequestParam("paymentId") int paymentId) {
        try{
            return ResponseEntity.ok(paymentService.findPaymentById(paymentId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/postPayment")
    public ResponseEntity<?> postPayment(@RequestBody PaymentRequest request) {
        try{
            return ResponseEntity.ok(paymentService.createPayment(request));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletePayment")
    public ResponseEntity<?> deletePayment(@RequestParam("paymentId") int paymentId) {
        try{
            return ResponseEntity.ok(paymentService.deletePaymentById(paymentId));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
