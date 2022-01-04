package dokerplp.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "users_next_id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private byte[] password;

    @Column(name = "salt")
    private byte[] salt;

    public UserEntity(String login, byte[] password, byte[] salt) {
        this.login = login;
        this.password = password;
        this.salt = salt;
    }
}