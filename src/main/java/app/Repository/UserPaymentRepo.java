package app.Repository;

import app.Entity.User;
import app.Entity.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPaymentRepo extends JpaRepository<UserPayment, Long> {

    List<UserPayment> findByUser(User user);
}
