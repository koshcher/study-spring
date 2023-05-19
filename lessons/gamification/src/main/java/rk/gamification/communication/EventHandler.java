package rk.gamification.communication;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rk.gamification.GameService;

@Component
public class EventHandler {

    @Autowired
    private GameService gameService;

    @RabbitListener(queues = "${translation.queue}")
    void handleTranslationCompleted(TranslationCompletedEvent event) {
        try {
            gameService.newUserAttempt(event.getUserId(), event.getTranslationResultAttemptId(), event.isCorrect());
        } catch (Exception e) {
            throw  new AmqpRejectAndDontRequeueException(e);
        }
    }
}
