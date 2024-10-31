package app.Controller;

import app.DTO.UserDTO;
import app.Entity.User;
import app.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody User user){
        userService.registerUser(user);
    }

    @DeleteMapping("/delete/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
       return userService.getAllUsers();
    }

    @GetMapping("/search/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO searchUser(@PathVariable String username){
        return userService.searchUser(username);
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String testEndpoint() {
        return "Test successful!";
    }
}
