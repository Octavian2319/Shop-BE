package app.Repository;

import app.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {



    Customer getCustomerById(Long id);
    Optional<Customer> findByUsername(String userName);
    Customer getCustomersByUsername(String userName);
}
