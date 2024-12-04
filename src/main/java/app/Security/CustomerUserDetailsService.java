package app.Security;

import app.Entity.Customer;
import app.Repository.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
        private final CustomerRepository customerRepository;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode("password");
        public CustomerUserDetailsService(CustomerRepository customerRepository){
            this.customerRepository=customerRepository;
        }

    private final Map<String, String> userDatabase = Map.of(
            "user", hashedPassword // "password" (hashed)
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customerEntity = customerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(customerEntity.getUsername())
                .password(customerEntity.getPassword()) // Hashed password from DB
                .roles(customerEntity.getRole()) // Use roles stored in the DB
                .build();
    }

}

