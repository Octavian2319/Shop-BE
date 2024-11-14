package app.Controller;

import app.DTO.UserPaymentDTO;
import app.Entity.UserPayment;
import app.Service.UserPaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class UserPaymentController {
    private final UserPaymentService userPaymentService;

    @PostMapping("/user/{username}/addpayment")
    public void addPayment(@PathVariable("username") String username, @RequestBody UserPayment userPayment){
        userPaymentService.saveUserPayment(username, userPayment);
    }

   @GetMapping("/user/{username}/payments/{id}")
    public UserPaymentDTO getPayments(@PathVariable("username") String username, @PathVariable("id") Long id){
        return userPaymentService.getUserPaymentByID(username,id);
  }

  @GetMapping("/user/{username}/payments")
    public List<UserPaymentDTO> deletePayment(@PathVariable("username") String username){
       return userPaymentService.getAllPayments(username);
    }

    @DeleteMapping("/user/{username}/payments/{id}")
    public void deletePayment(@PathVariable("username") String username, @PathVariable("id") Long id){
        userPaymentService.deletePayment(username,id);
    }
}