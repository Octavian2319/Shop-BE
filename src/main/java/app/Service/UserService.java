package app.Service;

import app.DTO.UserDTO;
import app.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void registerUser(User user);
    void deleteUser(String username);
    List<User> getAllUsers();
    UserDTO searchUser(String username);
}
