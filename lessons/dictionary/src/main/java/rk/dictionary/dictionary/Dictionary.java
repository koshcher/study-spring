package rk.dictionary.dictionary;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Data
@Entity
@Table(name = "dictionaries")
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String word;
    private String translation;

    public Dictionary(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public Dictionary() {

    }
}
