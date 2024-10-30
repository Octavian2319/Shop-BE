package app.Service;

import app.Entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void registerUser(User user);
    void deleteUser(String username);
}
