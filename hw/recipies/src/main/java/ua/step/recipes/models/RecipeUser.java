package ua.step.recipes.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class RecipeUser {

    @Id
    @GeneratedValue

    private Long id;
    private String login;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    private List<String> roles;

}
