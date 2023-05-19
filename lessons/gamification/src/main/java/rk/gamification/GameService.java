package rk.gamification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private ScoreCardRepository scoreCardRepository;

    public GameStats newUserAttempt(Long userId, Long attemptId, boolean correct) {
        if(!correct) return GameStats.empty(userId);

        ScoreCard scoreCard = new ScoreCard(userId, attemptId);
        scoreCardRepository.save(scoreCard);
        return new GameStats(userId, scoreCard.getScore());
    }

    GameStats retrieveUserStats(Long userId) {
        try {
            int score = scoreCardRepository.getTotalUserScore(userId);
            return new GameStats(userId, score);
        } catch (Exception exception) {
            return new GameStats(userId, 0);
        }
    }
}
