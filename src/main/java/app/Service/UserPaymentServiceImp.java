package app.Service;

import app.DTO.UserPaymentDTO;
import app.Entity.User;
import app.Entity.UserPayment;
import app.Repository.UserPaymentRepo;
import app.Repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserPaymentServiceImp implements UserPaymentService {
    private final UserPaymentRepo userPaymentRepo;
    private final UserRepo userRepo;

    @Override
    public void saveUserPayment(String username, UserPayment userPayment) {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            userPayment.setUser(user);
            userPaymentRepo.save(userPayment);
            log.info("Added a new payment for user ");
        } else {
            log.info("User not found");
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public UserPaymentDTO getUserPaymentByID(String username, Long id) {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            log.info("User not found");
            throw new RuntimeException("User not found");
        }

        Optional<UserPayment> userPaymentOptional = userPaymentRepo.findById(id);

        if (userPaymentOptional.isEmpty()) {
            log.info("Payment not found");
            throw new RuntimeException("Payment not found");
        }

        UserPayment userPayment = userPaymentOptional.get();

        if (!userPayment.getUser().equals(user)) {
            log.info("Payment does not belong to the user");
            throw new RuntimeException("Payment does not belong to the user");
        }

        UserPaymentDTO userPaymentDTO = new UserPaymentDTO();
        userPaymentDTO.setHolderName(userPayment.getHolderName());

        String cardNumber = userPayment.getCardNumber();
        if (cardNumber == null || cardNumber.length() < 4) {
            log.info("Invalid card number");
            throw new RuntimeException("Invalid card number");
        }

        userPaymentDTO.setLast4Digits(cardNumber.substring(cardNumber.length() - 4));
        userPaymentDTO.setExpiryYear(userPayment.getExpiryYear());
        userPaymentDTO.setExpiryMonth(userPayment.getExpiryMonth());

        return userPaymentDTO;
    }


    @Override
    public List<UserPaymentDTO> getAllPayments(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.info("User not found");
            throw new RuntimeException("User not found");
        }
        List<UserPayment> userPayments = userPaymentRepo.findByUser(user);
        return userPayments.stream()
                .map(payment -> {
                    UserPaymentDTO userPaymentDTO = new UserPaymentDTO();
                    userPaymentDTO.setHolderName(payment.getHolderName());
                    String cardNumber = payment.getCardNumber();
                    if (cardNumber == null || cardNumber.length() < 4) {
                        log.info("Invalid card number");
                        throw new RuntimeException("Invalid card number");
                    }
                    userPaymentDTO.setLast4Digits(cardNumber.substring(cardNumber.length() - 4));
                    userPaymentDTO.setExpiryYear(payment.getExpiryYear());
                    userPaymentDTO.setExpiryMonth(payment.getExpiryMonth());
                    return userPaymentDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deletePayment(String username, Long id) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.info("User not found");
            throw new RuntimeException("User not found");
        }
        Optional<UserPayment> userPaymentOptional = userPaymentRepo.findById(id);
        if (userPaymentOptional.isEmpty()) {
            log.info("Payment not found");
            throw new RuntimeException("Payment not found");
        }
        UserPayment userPayment = userPaymentOptional.get();
        if (!userPayment.getUser().equals(user)) {
            log.info("Payment does not belong to the user");
            throw new RuntimeException("Payment does not belong to the user");
        }
        userPaymentRepo.delete(userPayment);
    }
}
