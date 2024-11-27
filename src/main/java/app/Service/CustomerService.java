package app.Service;

import app.DTO.UserDTO;
import app.Entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    void registerUser(Customer customer);
    void deleteUser(String username);
    List<Customer> getAllUsers();
    UserDTO searchUser(String username);
    UserDTO searchUserById(Long id);
}
