package hotel.hotel_management.controller;

import hotel.hotel_management.modal.request.AccountRequest;
import hotel.hotel_management.service.account.AccountService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/listAccount")
    @PreAuthorize("hasAuthority('HOTELIER')")
    public ResponseEntity<?> listHotel(
            @RequestParam("hotelId") int hotelId
    ) {
        try{
            return ResponseEntity.ok(accountService.findByHotel(hotelId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("List of hotel failed");
        }
    }

    @GetMapping("/accountNumber")
    @PreAuthorize("hasAnyAuthority('HOTELIER','RECEPTIONIST')")
    public ResponseEntity<?> getAccountNumber(@RequestParam("accountId") int accountId) {
        try{
            return ResponseEntity.ok(accountService.findById(accountId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Account number not found");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountRequest request) {
        try{
            return ResponseEntity.ok(accountService.createHotelier(request));
        } catch (MessagingException e) {
            return ResponseEntity.badRequest().body("Register failed");
        }
    }

    @PostMapping("/createReceptionist")
    public ResponseEntity<?> createReceptionist(@RequestBody AccountRequest request) {
        try{
            return ResponseEntity.ok(accountService.createReceptionist(request));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Create receptionist failed");
        }
    }

    @PutMapping("/updateAccount")
    public ResponseEntity<?> updateAccount(@RequestBody AccountRequest request, @RequestParam("accountId") int accountId) {
        try {
            return ResponseEntity.ok(accountService.updateAccount(request, accountId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Update account failed");
        }
    }

    @PutMapping("/close_openAccount")
    public ResponseEntity<?> closeOpenAccount(@RequestParam("accountId") int accountId) {
        try{
            return ResponseEntity.ok(accountService.updateStatusAccount(accountId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Close or Open failed");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        try{
            return ResponseEntity.ok(accountService.login(email, password));
        } catch (MessagingException e) {
            return ResponseEntity.badRequest().body("Login failed");
        }
    }
}
