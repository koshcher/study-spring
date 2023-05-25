package rk.hiberent.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "apartments")
@NoArgsConstructor
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private Landlord landlord;

    private int price;
    private String district;
    private int roomCount;

    public Apartment(
            int price, String district, int roomCount,
            Landlord landlord
    ) {
        this.price = price;
        this.district = district;
        this.roomCount = roomCount;
        this.landlord = landlord;
    }

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL) //, cascade = CascadeType.ALL, orphanRemoval = true
    private List<Rental> rentals = new ArrayList<>();
}
