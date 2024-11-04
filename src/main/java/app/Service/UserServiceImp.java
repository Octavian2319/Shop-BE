package app.Service;

import app.DTO.UserDTO;
import app.Entity.User;
import app.Repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class UserServiceImp implements UserService {
    private final UserRepo userRepo;

    @Override
    public void registerUser(User user) {
        log.info("User registered");
        userRepo.save(user);
    }
    @Override
    public void deleteUser(String username){
        User user = userRepo.findByUsername(username);
        if(user == null){
            log.info("User not found");
            throw new RuntimeException("User not found");
        }
        else {
            log.info("User deleted");
            userRepo.delete(user);
        }

    }

    @Override
    public List<User> getAllUsers() {
        log.info("All users returned");
        return userRepo.findAll();
    }

    @Override
    public UserDTO searchUser(String username) {
        User user= userRepo.findByUsername(username);
        if(user==null){
            log.info("User not found");
            throw new RuntimeException("User not found");
        }
        else{
            log.info("User found");
            UserDTO userDto = new UserDTO();
            userDto.setUsername(user.getUsername());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            return userDto;
        }
    }

    @Override
    public UserDTO searchUserById(Long id) {
        User user=userRepo.findById(id).orElse(null);
        if(user==null){
            log.info("User not found");
            throw new RuntimeException("User not found");
        }
        else {
            log.info("User found");
            UserDTO userDto = new UserDTO();
            userDto.setUsername(user.getUsername());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            return userDto;
        }
    }

}
