package app.Service;

import app.DTO.CustomerDTO;
import app.Entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    void registerUser(Customer customer);

    void deleteUser(String username);
    List<Customer> getAllUsers();
    CustomerDTO searchUserById(Long id);

    CustomerDTO loginCustomer(String customerName, String password);
}
