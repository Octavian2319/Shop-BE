package app.Controller;

import app.Entity.ShippingAddress;
import app.Service.CustomerAddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController

public class CustomerAddressController {
    private final CustomerAddressService customerAddressService;

    public CustomerAddressController(CustomerAddressService customerAddressService) {
        this.customerAddressService = customerAddressService;
    }

    @PostMapping("/{username}/addAddress")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAddress(@PathVariable("username") String username, @RequestBody ShippingAddress shippingAddress) {
        customerAddressService.saveUserAddress(username, shippingAddress);
    }
    @PutMapping("/{username}/updateAddress")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateAddress(@PathVariable("username") String username, @RequestBody ShippingAddress shippingAddress){
        customerAddressService.updateUserAddress(username, shippingAddress);
    }
    @DeleteMapping("/{username}/deleteAddress/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAddress(@PathVariable("username") String username, @PathVariable("id") Long id){
        customerAddressService.deleteUserAddress(username,id);
    }
    @GetMapping("/{username}/getAddress/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ShippingAddress getAddress(@PathVariable("username") String username, @PathVariable("id") Long id){
        return customerAddressService.getUserAddress(username,id);
    }

}
