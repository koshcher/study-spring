package rk.hiber1.db;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "towns")
@NoArgsConstructor
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private long residentsCount;
    private String history;

    private double x;
    private double y;
}
