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
    private String telephone;
    @NonNull
    private String email;
    @NonNull
    private String role;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<ShippingAddress> shippingAddresses;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<CustomerPayment> customerPayments;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Customer(@NonNull String username, @NonNull String password, @NonNull String firstName, @NonNull String lastName, @NonNull String phone, @NonNull String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = phone;
        this.email = email;
        this.customerPayments = null;
        this.cart = null;
        this.shippingAddresses = null;
    }

    public CustomerPayment getPaymentMethod(int nr){
        return customerPayments.get(nr);
    }


}
