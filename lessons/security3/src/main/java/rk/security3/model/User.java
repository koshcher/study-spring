package rk.security3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<UserRole> roles;

    public User(String username, String password, List<UserRole> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
