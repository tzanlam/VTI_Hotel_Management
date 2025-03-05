package hotel.hotel_management.controller;

import hotel.hotel_management.modal.request.InvoiceRequest;
import hotel.hotel_management.service.invoice.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/getInvoiceByHotelId")
    public ResponseEntity<?> findInvoiceByHotelId(@RequestParam("hotelId") int hotelId) {
        try{
            return ResponseEntity.ok(invoiceService.findInvoiceByHotelId(hotelId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getInvoiceByAccountId")
    public ResponseEntity<?> findInvoiceByAccountId(@RequestParam("accountId") int accountId) {
        try{
            return ResponseEntity.ok(invoiceService.findByAccountId(accountId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getInvoiceById")
    public ResponseEntity<?> findInvoiceById(@RequestParam("invoiceId") int invoiceId) {
        try{
            return ResponseEntity.ok(invoiceService.findInvoiceById(invoiceId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/postInvoice")
    public ResponseEntity<?> postInvoice(@RequestBody InvoiceRequest re) {
        try {
            return ResponseEntity.ok(invoiceService.save(re));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/postStatus")
    public ResponseEntity<?> updateStatus(@RequestParam("invoiceId") int invoiceId) {
        try{
            return ResponseEntity.ok(invoiceService.setStatus(invoiceId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteInvoice")
    public ResponseEntity<?> deleteInvoice(@RequestParam("invoiceId") int invoiceId) {
        try{
            return ResponseEntity.ok(invoiceService.deleteInvoice(invoiceId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}