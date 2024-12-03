package app.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "payment_details")
@RequiredArgsConstructor
@Data
@NoArgsConstructor
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private Integer order_id;

    @NonNull
    private Integer amount;

    @NonNull
    private String provider;

    @NonNull
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_payment_id")
    private CustomerPayment customerPayment;
}
