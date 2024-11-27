package app.Service;

import app.Entity.Customer;
import app.Entity.ShippingAddress;
import app.Repository.CustomerAddressRepository;
import app.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerAddressServiceImp implements CustomerAddressService {
    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void saveUserAddress(String username, ShippingAddress shippingAddress) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            log.info("User not found for address");
            throw new RuntimeException("User not found for address");
        } else {
            shippingAddress.setCustomer(customer);
            customerAddressRepository.save(shippingAddress);
            log.info("User address saved");
        }
    }

    @Override
    public void updateUserAddress(String username, ShippingAddress shippingAddress) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            log.info("User not found on update address");
            throw new RuntimeException("User not found on update address");
        } else {
            shippingAddress.setCustomer(customer);
            customerAddressRepository.save(shippingAddress);
            log.info("User address updated");
        }
    }

    @Override
    public void deleteUserAddress(String username, Long id) {
        Customer customer = customerRepository.findByUsername(username);
        ShippingAddress shippingAddress = customerAddressRepository.findById(id).orElse(null);
        if (customer == null || shippingAddress == null) {
            log.info("User or address not found for delete");
            throw new RuntimeException("User or address not found for delete");
        } else {
            customerAddressRepository.deleteById(id);
            log.info("User address deleted");
        }
    }

    @Override
    public ShippingAddress getUserAddress(String username, Long id) {
        Customer customer = customerRepository.findByUsername(username);
        ShippingAddress shippingAddress = customerAddressRepository.findById(id).orElse(null);
        if (customer == null || shippingAddress == null) {
            log.info("User or address not found from address");
            throw new RuntimeException("User or address not found from address");
        } else {
            return shippingAddress;
        }
    }
}
