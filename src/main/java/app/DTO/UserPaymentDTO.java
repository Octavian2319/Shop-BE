package app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPaymentDTO {
    private String holderName;
    private int expiryMonth;
    private int expiryYear;
    private String last4Digits;
}
