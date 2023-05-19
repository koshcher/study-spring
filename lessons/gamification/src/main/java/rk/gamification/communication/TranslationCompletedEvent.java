package rk.gamification.communication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslationCompletedEvent implements Serializable {
    private Long translationResultAttemptId;

    private Long userId;

    private boolean correct;


}
