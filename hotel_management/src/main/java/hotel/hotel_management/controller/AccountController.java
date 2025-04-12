package hotel.hotel_management.controller;

import hotel.hotel_management.modal.request.AccountRequest;
import hotel.hotel_management.service.account.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/getAccounts")
    public ResponseEntity<?> GetAccounts() {
        try{
            return ResponseEntity.ok(accountService.getAllAccounts());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong when getting accounts");
        }
    }

    @GetMapping("/getAccountById")
    @PreAuthorize("hasAnyAuthority('HOTELIER', 'MANAGER', 'RECEPTION')")
    public ResponseEntity<?> GetAccountById(@RequestParam("accountId") int id) {
        try{
            return ResponseEntity.ok(accountService.getAccountById(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong when getting account by id");
        }
    }

    @GetMapping("/getHotelByAccountId")
    public ResponseEntity<?> GetHotelByAccountId(@RequestParam("accountId") int id) {
        try{
            return ResponseEntity.ok(accountService.getByAccountId(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong when getting hotel by id");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> RegisterAccount(@RequestBody AccountRequest request) {
        try{
            return ResponseEntity.ok(accountService.register(request));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong when registering account");
        }
    }

    @PostMapping("/reception")
    public ResponseEntity<?> ReceptionAccount(@RequestBody AccountRequest request) {
        try{
            return ResponseEntity.ok(accountService.reception(request));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong when create reception account");
        }
    }

    @PostMapping("/manager")
    public ResponseEntity<?> ManagerAccount(@RequestBody AccountRequest request) {
        try{
            return ResponseEntity.ok(accountService.manager(request));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong when create manager account");
        }
    }

    @PostMapping("/evaluateEmployee")
    public ResponseEntity<?> EvaluateEmployee(@RequestParam("accountId") int accountId, @RequestParam("evaluate") String evaluate) {
        try{
            return ResponseEntity.ok(accountService.addEvaluate(accountId, evaluate));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong when add evaluate account");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestParam("email") String email, @RequestParam("password") String password) {
        try{
            return ResponseEntity.ok(accountService.login(email, password));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong when login");
        }
    }

    @PutMapping("/putAccount")
    public ResponseEntity<?> PutAccount(@RequestBody AccountRequest request, @RequestParam("accountId") int accountId) {
        try{
            return ResponseEntity.ok(accountService.updateAccount(request, accountId));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong when update account");
        }
    }

    @PutMapping("/forgotPassword")
    public ResponseEntity<?> ForgotPassword(@RequestParam("email") String email) {
        try{
            return ResponseEntity.ok(accountService.forgotPassword(email));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong when forgot password");
        }
    }

    @PutMapping("/statusAccount")
    public ResponseEntity<?> DeleteAccount(@RequestParam("accountId") int accountId) {
        try{
            return ResponseEntity.ok(accountService.deleteAccount(accountId));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong when update status account");
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> Confirm(@RequestParam("email") String email, @RequestParam("code") String code) {
        try{
            return ResponseEntity.ok(accountService.confirm(email, code));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong when confirm");
        }
    }
}
