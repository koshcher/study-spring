package ua.step.security.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "roles")
@RequiredArgsConstructor
@NoArgsConstructor
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String name;


    @Override
    public String getAuthority() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
