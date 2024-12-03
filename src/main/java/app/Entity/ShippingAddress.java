package app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "user_address")
@RequiredArgsConstructor
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String address_line1;
    private String address_line2;
    @NonNull
    private String city;
    @NonNull
    private String state;
    @NonNull
    private String country;
    @NonNull
    private Integer postal_code;
    @NonNull
    private int telephone;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Customer customer;
}
