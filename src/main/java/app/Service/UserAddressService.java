package app.Service;

import app.Entity.UserAddress;
import org.springframework.stereotype.Service;

@Service
public interface UserAddressService {
    public void saveUserAddress(String username, UserAddress userAddress);
    public void updateUserAddress(String username, UserAddress userAddress);
    public void deleteUserAddress(String username, Long id);
    public UserAddress getUserAddress(String username, Long id);
}
