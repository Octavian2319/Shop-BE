package app.Service;

import app.DTO.CustomerDTO;
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
    public CustomerDTO searchUser(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if(customer ==null){
            log.info("User not found by username");
            throw new RuntimeException("User not found by username");
        }
        else{
            log.info("User found by username");
            CustomerDTO customerDto = new CustomerDTO();
            customerDto.setUsername(customer.getUsername());
            customerDto.setFirstName(customer.getFirstName());
            customerDto.setLastName(customer.getLastName());
            return customerDto;
        }
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

    @Override
    public CustomerDTO loginCustomer(String customerName, String password) {
        //ENCRYPT PAROLA
        Customer customer;
        try {
            customer = customerRepository.findByUsername(customerName);
        }catch (Exception e){
            throw new RuntimeException("User not found");
        }
        if(customer.getPassword().equals(password))
            return new CustomerDTO(customer);
        else
            return null;

    }

}
