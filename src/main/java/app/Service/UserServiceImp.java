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


}
