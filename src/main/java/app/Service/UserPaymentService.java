package app.Service;

import app.DTO.UserPaymentDTO;
import app.Entity.UserPayment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserPaymentService {
    void saveUserPayment(String username, UserPayment userPayment);

    UserPaymentDTO getUserPaymentByID(String username,Long id);
     List<UserPaymentDTO> getAllPayments(String username);

    public void deletePayment(String username, Long id);
}
