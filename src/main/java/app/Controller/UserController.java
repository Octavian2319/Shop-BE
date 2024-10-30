package app.Controller;

import app.Entity.User;
import app.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String testEndpoint() {
        return "Test successful!";
    }
}