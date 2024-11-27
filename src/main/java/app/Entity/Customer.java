package app.Entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_table")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private Integer telephone;
    @NonNull
    private String email;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<ShippingAddress> shippingAddresses;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<CustomerPayment> customerPayments;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public CustomerPayment getPaymentMethod(int nr){
        return customerPayments.get(nr);
    }


}
