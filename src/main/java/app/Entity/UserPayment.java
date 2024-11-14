package app.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@Table(name = "user_payment")
@AllArgsConstructor
@NoArgsConstructor
public class UserPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String cardNumber;
    @NonNull
    private int expiryMonth;
    @NonNull
    private int expiryYear;
    @NonNull
    private int cvc;
    @NonNull
    private String holderName;
    private boolean defaultPayment=true;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
