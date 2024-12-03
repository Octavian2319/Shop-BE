package app.Service;

import app.Entity.ShippingAddress;
import org.springframework.stereotype.Service;

@Service
 public interface CustomerAddressService {
     void saveUserAddress(String username, ShippingAddress shippingAddress);
     void updateUserAddress(String username, ShippingAddress shippingAddress);
     void deleteUserAddress(String username, Long id);
     ShippingAddress getUserAddress(String username, Long id);
}
