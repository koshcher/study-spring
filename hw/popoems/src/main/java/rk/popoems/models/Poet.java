package rk.popoems.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "poets")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Poet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String name;
    @Column(nullable = false)
    @NonNull
    private String surname;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    @NonNull private String bio;

    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "poet_id")
    @NonNull List<Poem> poems;
}
