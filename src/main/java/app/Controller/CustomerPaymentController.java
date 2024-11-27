package app.Controller;

import app.DTO.CustomerPaymentDTO;
import app.Entity.CustomerPayment;
import app.Service.CustomerPaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class CustomerPaymentController {
    private final CustomerPaymentService customerPaymentService;

    @PostMapping("/user/{id}/addpayment")
    public void addPayment(@PathVariable Long id,@RequestBody CustomerPayment customerPayment){
        customerPaymentService.saveCustomerPayment(id,customerPayment);
    }

   @GetMapping("/user/{customerId}/payments/{nr}")
    public CustomerPaymentDTO getPayments(@PathVariable("customerId") Long customerId, @PathVariable("nr") int nr){
        return customerPaymentService.getUserPayment(customerId,nr);
  }

  @GetMapping("/user/{customerId}/payments")
    public List<CustomerPaymentDTO> deletePayment(@PathVariable("customerId") Long customerId){
       return customerPaymentService.getAllPayments(customerId);
    }

    @DeleteMapping("/user/{username}/payments/{id}")
    public void deletePayment(@PathVariable("username") String username, @PathVariable("id") Long id){
        customerPaymentService.deletePayment(username,id);
    }
}