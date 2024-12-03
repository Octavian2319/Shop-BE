package app.Service;

import app.DTO.CustomerPaymentDTO;
import app.Entity.Customer;
import app.Entity.CustomerPayment;
import app.Repository.CustomerPaymentRepository;
import app.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class CustomerPaymentServiceImp implements CustomerPaymentService {
    private final CustomerPaymentRepository customerPaymentRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void saveCustomerPayment(Long id, CustomerPayment customerPayment) {
        Customer customer = customerRepository.getCustomerById(id);
        if (customer != null) {
            customerPayment.setCustomer(customer);
            customerPaymentRepository.save(customerPayment);

        } else {
            log.info("User not found");
            throw new RuntimeException("User not found");
        }
    }



    @Override
    public CustomerPaymentDTO getUserPayment(Long customerId, int nr) {
        Customer customer;
        CustomerPayment customerPayment;
        try{
            customer = customerRepository.getCustomerById(customerId);
        }
        catch (Exception e){
            throw new RuntimeException("User not found");
        }
        try{
            customerPayment = customer.getPaymentMethod(nr-1);
        }
        catch (Exception e){
            throw new RuntimeException("Payment method not found");
        }
        return new CustomerPaymentDTO(customerPayment);


    }


    @Override
    public List<CustomerPaymentDTO> getAllPayments(Long customerId) {
        Customer customer;
        try {
            customer = customerRepository.getCustomerById(customerId);
        }catch (Exception e){
            throw new RuntimeException("User not found");
        }
        List<CustomerPayment> customerPayments=customer.getCustomerPayments();
        List<CustomerPaymentDTO> customerPaymentDTO=new ArrayList<>();
        customerPayments.forEach(customerPayment->customerPaymentDTO.add(new CustomerPaymentDTO(customerPayment)));

        return customerPaymentDTO;
    }

    @Override
    public void deletePayment(String username, Long id) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            log.info("User not found");
            throw new RuntimeException("User not found");
        }
        Optional<CustomerPayment> userPaymentOptional = customerPaymentRepository.findById(id);
        if (userPaymentOptional.isEmpty()) {
            log.info("Payment not found");
            throw new RuntimeException("Payment not found");
        }
        CustomerPayment customerPayment = userPaymentOptional.get();
        if (!customerPayment.getCustomer().equals(customer)) {
            log.info("Payment does not belong to the user");
            throw new RuntimeException("Payment does not belong to the user");
        }
        customerPaymentRepository.delete(customerPayment);
    }
}
