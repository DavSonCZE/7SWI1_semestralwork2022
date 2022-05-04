package cz.davson.reviewgameapp.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor

public class User {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_user_id")
    private Long id;

    @Getter
    @Setter
    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Getter
    @Setter
    @NotNull
    @Column(name = "surname")
    private String surname;

    @Getter
    @Setter
    @NotNull
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @NotNull
    @Column(name = "username")
    private String userName;

    public User(String firstName, String surname, String email, String userName) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.userName = userName;
    }
}