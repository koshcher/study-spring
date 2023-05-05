package ua.step.recipes.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NotFound;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @NonNull
    private String name;

    public Product(Long id) {
        this.id = id;
    }
}
