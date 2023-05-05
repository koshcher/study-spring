package ua.step.firstrun.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String login;
    @NonNull
    private String password;

    private LocalDateTime created;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Sex sex;

    @ManyToMany
    private Collection<Role> roles;

    @OneToMany
    @JoinColumn(referencedColumnName = "id")
    private Collection<Comment> comments;

    public enum Sex {
        Male,
        Female
    }
}
