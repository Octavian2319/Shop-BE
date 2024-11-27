package app.Service;

import app.DTO.CustomerPaymentDTO;
import app.Entity.CustomerPayment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerPaymentService {
    void saveCustomerPayment(Long id, CustomerPayment customerPayment);

    CustomerPaymentDTO getUserPayment(Long customerId, int nr);
    List<CustomerPaymentDTO> getAllPayments(Long customerId);

    void deletePayment(String username, Long id);
}
