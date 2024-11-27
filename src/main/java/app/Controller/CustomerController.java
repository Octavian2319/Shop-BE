package app.Controller;

import app.DTO.UserDTO;
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
@RequestMapping("/user")
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody Customer customer){
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

    @GetMapping("/search/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO searchUser(@PathVariable String username){
        return customerService.searchUser(username);
    }

    @GetMapping("/user/search/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO searchUserById(@PathVariable Long id){
        log.info("User searched by id");
        return customerService.searchUserById(id);
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String testEndpoint() {
        return "Test successful!";
    }
}
