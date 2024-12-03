package app.Controller;

import app.DTO.CustomerDTO;
import app.Entity.Customer;
import app.Service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping("/register")
//    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody Customer customer) {
        customerService.registerUser(customer);
    }

    @DeleteMapping("/delete/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String username){
        customerService.deleteUser(username);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllUsers(){
        return customerService.getAllUsers();
    }

    @GetMapping("/user/search/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO searchUserById(@PathVariable Long id){
        return customerService.searchUserById(id);
    }

    @PostMapping(path = "/login")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO login(@RequestBody String customerName, @RequestBody String password){
        return customerService.loginCustomer(customerName, password);
    }
}
