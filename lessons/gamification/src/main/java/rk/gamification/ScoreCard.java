package rk.gamification;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "scores")
public class ScoreCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private Long cardId;
    private Long userId;
    private Long attemptId;
    private LocalDateTime created;
    private int score = 5;

    public ScoreCard(Long userId, Long attemptId) {
        this.userId = userId;
        this.attemptId =attemptId;
        created = LocalDateTime.now();
    }
}
