package IntexSoft.Authorization.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "email", nullable = false, length = 60, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 25)
    private String password;

}

