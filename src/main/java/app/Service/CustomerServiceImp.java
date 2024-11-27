package app.Service;

import app.DTO.UserDTO;
import app.Entity.Customer;
import app.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public void registerUser(Customer customer) {
        log.info("User registered");
        customerRepository.save(customer);
    }
    @Override
    public void deleteUser(String username){
        Customer customer = customerRepository.findByUsername(username);
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
    public UserDTO searchUser(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if(customer ==null){
            log.info("User not found by username");
            throw new RuntimeException("User not found by username");
        }
        else{
            log.info("User found by username");
            UserDTO userDto = new UserDTO();
            userDto.setUsername(customer.getUsername());
            userDto.setFirstName(customer.getFirstName());
            userDto.setLastName(customer.getLastName());
            return userDto;
        }
    }

    @Override
    public UserDTO searchUserById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer ==null){
            log.info("User not found by ID");
            throw new RuntimeException("User not found by ID");
        }
        else {
            log.info("User found by ID");
            UserDTO userDto = new UserDTO();
            userDto.setUsername(customer.getUsername());
            userDto.setFirstName(customer.getFirstName());
            userDto.setLastName(customer.getLastName());
            return userDto;
        }
    }

}
