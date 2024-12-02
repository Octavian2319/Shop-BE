package app.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.security.Timestamp;
import java.util.List;


@Table(name = "order_details")
@RequiredArgsConstructor
@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Timestamp order_date;

    private double total_amount;

    @OneToOne
    @JoinColumn(name = "shipping_id")
    private ShippingAddress shippingAddress;

    private String order_status;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private PaymentDetails paymentDetails;

    @OneToMany
    @JoinColumn(name = "order_items_id")
    private List<OrderItem> orderItem;


}
