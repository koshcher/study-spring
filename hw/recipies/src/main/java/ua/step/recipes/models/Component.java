package ua.step.recipes.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
@RequiredArgsConstructor
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @NonNull
    private int amount;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @NonNull
    @ManyToOne
    private Product product;

    @NonNull
    @ManyToOne
    private Recipe recipe;

    public enum Unit {
        Kilogram,
        Liter,
        Gram,
        Piece
    }

    public Component(int amount, Unit unit, Product product) {
        this.amount = amount;
        this.unit = unit;
        this.product = product;
    }

    public Component(Product product) {
        this.product = product;
    }


}
