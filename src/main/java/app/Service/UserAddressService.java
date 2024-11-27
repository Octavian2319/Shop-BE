package app.Service;

import app.Entity.UserAddress;
import org.springframework.stereotype.Service;

@Service
 public interface UserAddressService {
     void saveUserAddress(String username, UserAddress userAddress);
     void updateUserAddress(String username, UserAddress userAddress);
     void deleteUserAddress(String username, Long id);
     UserAddress getUserAddress(String username, Long id);
}
