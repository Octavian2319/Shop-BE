package app.Service;

import app.Entity.User;
import app.Entity.UserAddress;
import app.Repository.UserAddressRepo;
import app.Repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserAddressServiceImp implements UserAddressService {
    private final UserAddressRepo userAddressRepo;
    private final UserRepo userRepo;

    @Override
    public void saveUserAddress(String username, UserAddress userAddress) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.info("User not found for address");
            throw new RuntimeException("User not found for address");
        } else {
            userAddress.setUser(user);
            userAddressRepo.save(userAddress);
            log.info("User address saved");
        }
    }

    @Override
    public void updateUserAddress(String username, UserAddress userAddress) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.info("User not found on update address");
            throw new RuntimeException("User not found on update address");
        } else {
            userAddress.setUser(user);
            userAddressRepo.save(userAddress);
            log.info("User address updated");
        }
    }

    @Override
    public void deleteUserAddress(String username, Long id) {
        User user = userRepo.findByUsername(username);
        UserAddress userAddress = userAddressRepo.findById(id).orElse(null);
        if (user == null || userAddress == null) {
            log.info("User or address not found for delete");
            throw new RuntimeException("User or address not found for delete");
        } else {
            userAddressRepo.deleteById(id);
            log.info("User address deleted");
        }
    }

    @Override
    public UserAddress getUserAddress(String username, Long id) {
        User user = userRepo.findByUsername(username);
        UserAddress userAddress = userAddressRepo.findById(id).orElse(null);
        if (user == null || userAddress == null) {
            log.info("User or address not found from address");
            throw new RuntimeException("User or address not found from address");
        } else {
            return userAddress;
        }
    }
}
