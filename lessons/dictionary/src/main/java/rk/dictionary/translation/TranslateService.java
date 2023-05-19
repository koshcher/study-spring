package rk.dictionary.translation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rk.dictionary.EventDispatcher;
import rk.dictionary.attempt.TranslateResultAttempt;
import rk.dictionary.attempt.TranslationResultAttemptRepository;
import rk.dictionary.dictionary.Dictionary;
import rk.dictionary.dictionary.DictionaryRepository;
import rk.dictionary.user.User;
import rk.dictionary.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TranslateService {
    @Autowired
    private DictionaryRepository dictionaryRepository;

    @Autowired
    private TranslationResultAttemptRepository attemptRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventDispatcher eventDispatcher;

    public String getRandom() {
        long total = dictionaryRepository.count();
        int index = (int)(Math.random() * total);
        Page<Dictionary> dictionaryPage = dictionaryRepository.findAll(PageRequest.of(index, 1));
        Optional<Dictionary> dictionary = dictionaryPage.get().findFirst();
        if(dictionary.isEmpty()) return "";
        return dictionary.get().getWord();
    }

    public boolean checkAttempt(TranslateResultAttempt attempt) {
        Optional<User> optionalUser = userRepository.getUserByName(attempt.getUser().getName());

        Optional<Dictionary> dictionary = dictionaryRepository.findByWord(attempt.getDictionary().getWord());
        if(dictionary.isEmpty()) return false;

        User user = optionalUser.orElse(attempt.getUser());
        boolean correct = dictionary.get().getTranslation().equalsIgnoreCase(attempt.getResultAttempt());

        TranslateResultAttempt resultAttempt = new TranslateResultAttempt(user, dictionary.get(), attempt.getResultAttempt(), correct);
        attemptRepository.save(resultAttempt);
        eventDispatcher.send(new TranslationCompletedEvent(resultAttempt.getId(), user.getId(), correct));
        return correct;
    }

    public List<TranslateResultAttempt> getUserStats(String username) {
       return attemptRepository.findTop5ByUserNameOrderByIdDesc(username);
    }
}
