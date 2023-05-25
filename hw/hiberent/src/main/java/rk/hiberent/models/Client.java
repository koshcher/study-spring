package rk.hiberent.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    /*
    @OneToOne
    @JoinColumn(name = "apartment_id")
    @Nullable
    private Apartment apartment;
     */

    private String name;
    private String surname;
    private String phone;

    private int preferRoomCount;
    private String preferDistrict;
    private int preferPrice;

    public Client(String name, String surname, String phone, int preferPrice, int preferRoomCount, String preferDistrict) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.preferDistrict = preferDistrict;
        this.preferPrice = preferPrice;
        this.preferRoomCount = preferRoomCount;
    }

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)//, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rental> rentals;
}
