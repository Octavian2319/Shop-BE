package app.Repository;


import app.Entity.CustomerPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerPaymentRepository extends JpaRepository<CustomerPayment, Long> {

}
