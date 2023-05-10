package rk.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rk.dictionary.attempt.TranslateResultAttempt;

@RestController
public class TranslateController {

    @Autowired
    private TranslateService translateService;


    @GetMapping("/translation/random")
    public String getRandom() {
        return translateService.getRandom();
    }

    @PostMapping(value = "/translation/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TranslateResultAttempt> postResult(@RequestBody TranslateResultAttempt attempt) {
        boolean correct = translateService.checkAttempt(attempt);
        TranslateResultAttempt resultAttempt = new TranslateResultAttempt(
                attempt.getUser(), attempt.getDictionary(), attempt.getResultAttempt(), correct
        );
        return ResponseEntity.ok(resultAttempt);
    }
}
