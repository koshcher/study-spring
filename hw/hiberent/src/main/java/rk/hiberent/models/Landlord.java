package rk.hiberent.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "landlords")
@NoArgsConstructor
public class Landlord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @OneToMany
    @JoinColumn(name = "landlord_id")
    private List<Apartment> apartments;

    private String name;
    private String surname;
    private String phone;

    public Landlord(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }
}
