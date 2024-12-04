package app.Security;

import app.Entity.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    private Long id;
    private String username;
    private String password;
    private String role;

    public UserEntity(Customer customer){
        this.id= customer.getId();
        this.username=customer.getUsername();
        this.password=customer.getPassword();
        this.role=customer.getRole();
    }

}
