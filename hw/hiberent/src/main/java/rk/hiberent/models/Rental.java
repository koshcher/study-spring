package rk.hiberent.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.StreamHandler;

@Data
@Entity
@Table(name = "rental")
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    private Apartment apartment;

    //private LocalDateTime start;
    //private LocalDateTime end;
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private LocalDate startDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private LocalDate endDate;

    public Rental(Apartment apartment, Client client,
                  LocalDate start, LocalDate end) {
        this.apartment = apartment;
        this.client = client;
        this.startDate = start;
        this.endDate = end;
    }


}
