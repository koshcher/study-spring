package rk.dictionary.attempt;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TranslationResultAttemptRepository extends CrudRepository<TranslateResultAttempt, Long> {

    List<TranslateResultAttempt> findTop5ByUserNameOrderByIdDesc(String username);
}
