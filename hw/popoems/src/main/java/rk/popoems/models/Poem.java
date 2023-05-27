package rk.popoems.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "poems")
public class Poem {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String name;


    @NonNull private String text;

    @Enumerated(EnumType.STRING)
    @NonNull private Category category;
}
