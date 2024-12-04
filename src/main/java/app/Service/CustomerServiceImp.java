package app.Service;

import app.DTO.CustomerDTO;
import app.Entity.Customer;
import app.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(Customer customer) {
        log.info("User registered");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        customer.setPassword(encoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }
    @Override
    public void deleteUser(String username){
        Customer customer = customerRepository.getCustomersByUsername(username);
        if(customer == null){
            log.info("User not found");
            throw new RuntimeException("User not found");
        }
        else {
            log.info("User deleted");
            customerRepository.delete(customer);
        }

    }

    @Override
    public List<Customer> getAllUsers() {
        log.info("All users returned");
        return customerRepository.findAll();
    }


    @Override
    public CustomerDTO searchUserById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer ==null){
            log.info("User not found by ID");
            throw new RuntimeException("User not found by ID");
        }
        else {
            log.info("User found by ID");
            CustomerDTO customerDto = new CustomerDTO();
            customerDto.setUsername(customer.getUsername());
            customerDto.setFirstName(customer.getFirstName());
            customerDto.setLastName(customer.getLastName());
            return customerDto;
        }
    }

//    @Override
//    public CustomerDTO loginCustomer(String customerUserName, String password) {
//        //ENCRYPT PAROLA
//        Customer customer;
//        try {
//            customer = customerRepository.getCustomersByUsername(customerUserName);
//        }catch (Exception e){
//            throw new RuntimeException("User not found");
//        }
//        if(customer.getPassword().equals(password))
//            return new CustomerDTO(customer);
//        else
//            return null;
//
//    }

}
