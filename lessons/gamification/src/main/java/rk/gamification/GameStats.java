package rk.gamification;

import lombok.Data;

@Data
public class GameStats {
    private Long userId;
    private int score = 10;

    public GameStats(Long userId, int score) {
        this.score = score;
        this.userId = userId;
    }

    public static GameStats empty(Long userId) {
        return new GameStats(userId, 10);
    }
}
