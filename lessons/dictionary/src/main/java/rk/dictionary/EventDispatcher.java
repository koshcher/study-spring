package rk.dictionary;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rk.dictionary.translation.TranslationCompletedEvent;

@Component
public class EventDispatcher {
    private final RabbitTemplate template;
    private final String translationExchange;
    private final String translationCompleted;

    @Autowired
    public EventDispatcher(
            RabbitTemplate template,
            @Value("${translation.exchange}") String translationExchange,
            @Value("${translation.completed.key}") String translationCompleted
    ) {
        this.template =template;
        this.translationExchange = translationExchange;
        this.translationCompleted = translationCompleted;
    }

    public void send(TranslationCompletedEvent event) {
        template.convertAndSend(translationExchange, translationCompleted, event);
    }
}
