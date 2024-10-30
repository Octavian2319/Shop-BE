package app.Service;

import app.Entity.User;
import app.Repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {
    private final UserRepo userRepo;

    @Override
    public void registerUser(User user) {
        System.out.println("User registered");
        userRepo.save(user);
    }
    @Override
    public void deleteUser(String username){
        User user = userRepo.findByUsername(username);
        if(user == null){
            System.out.println("User not found");
            throw new RuntimeException("User not found");
        }
        else {
            System.out.println("User deleted");
            userRepo.delete(user);
        }

    }

}
