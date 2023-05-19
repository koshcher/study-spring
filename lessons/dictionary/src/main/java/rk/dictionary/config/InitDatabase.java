package rk.dictionary.config;

import com.sun.net.httpserver.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import rk.dictionary.dictionary.Dictionary;
import rk.dictionary.dictionary.DictionaryRepository;

@Component
public class InitDatabase implements CommandLineRunner {
    @Autowired
    DictionaryRepository dictionaryRepository;

    @Override
    public void run(String... args) throws Exception {
        dictionaryRepository.save(new Dictionary("cat", "кот"));
        dictionaryRepository.save(new Dictionary("black", "черний"));
        dictionaryRepository.save(new Dictionary("duck", "утка"));
    }


}
