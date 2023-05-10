package rk.dictionary.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rk.dictionary.dictionary.Dictionary;

import java.util.Optional;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
    Optional<Dictionary> findByWord(String word);
}
