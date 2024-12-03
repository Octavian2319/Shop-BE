package app.DTO;

import app.Entity.CustomerPayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerPaymentDTO {
    private String holderName;
    private int expiryMonth;
    private int expiryYear;
    private String last4Digits;

    public CustomerPaymentDTO(CustomerPayment customerPayment) {
        this.holderName = customerPayment.getHolderName();
        this.expiryMonth = customerPayment.getExpiryMonth();
        this.expiryYear = customerPayment.getExpiryYear();
        this.last4Digits = customerPayment.getCardNumber().substring(customerPayment.getCardNumber().length()-4);
    }
}
