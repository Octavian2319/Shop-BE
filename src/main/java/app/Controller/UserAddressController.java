package app.Controller;

import app.Entity.UserAddress;
import app.Service.UserAddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserAddressController {
    private final UserAddressService userAddressService;

    @PostMapping("/{username}/addAddress")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAddress(@PathVariable("username") String username, @RequestBody UserAddress userAddress) {
        userAddressService.saveUserAddress(username, userAddress);
    }
    @PutMapping("/{username}/updateAddress")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateAddress(@PathVariable("username") String username, @RequestBody UserAddress userAddress){
        userAddressService.updateUserAddress(username,userAddress);
    }
    @DeleteMapping("/{username}/deleteAddress/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAddress(@PathVariable("username") String username, @PathVariable("id") Long id){
        userAddressService.deleteUserAddress(username,id);
    }
    @GetMapping("/{username}/getAddress/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserAddress getAddress(@PathVariable("username") String username, @PathVariable("id") Long id){
        return userAddressService.getUserAddress(username,id);
    }

}
