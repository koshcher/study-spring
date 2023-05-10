package rk.dictionary.attempt;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import rk.dictionary.dictionary.Dictionary;
import rk.dictionary.user.User;

@Data
@Entity
@Table
@AllArgsConstructor
public class TranslateResultAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name =  "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;

    private String resultAttempt;
    private boolean isCorrect;

    public TranslateResultAttempt(User user, Dictionary dictionary, String resultAttempt, boolean isCorrect) {
        this.user = user;
        this.dictionary = dictionary;
        this.resultAttempt = resultAttempt;
        this.isCorrect = isCorrect;
    }

    public TranslateResultAttempt() {}
}
